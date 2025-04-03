import java.util.concurrent.*
import kotlin.math.min
import kotlin.random.Random

class Island(val width: Int, val height: Int) {
    val cells = Array(width) { Array(height) { Cell() } }
    private val scheduledExecutor = Executors.newScheduledThreadPool(3)
    private val taskExecutor = ForkJoinPool(4)

    fun startSimulation() {
        initializeAnimals()
        scheduledExecutor.scheduleAtFixedRate({ updatePlants() }, 0, 1, TimeUnit.SECONDS)
        scheduledExecutor.scheduleAtFixedRate({ updateAnimals() }, 0, 1, TimeUnit.SECONDS)
        scheduledExecutor.scheduleAtFixedRate({ printStats() }, 0, 1, TimeUnit.SECONDS)
    }

    private fun initializeAnimals() {
        repeat(50) { addRandomAnimal(Wolf::class.java) }
        repeat(30) { addRandomAnimal(Rabbit::class.java) }
    }

    private fun <T : Animal> addRandomAnimal(animalClass: Class<T>) {
        val x = Random.nextInt(width)
        val y = Random.nextInt(height)
        val animal = animalClass.getDeclaredConstructor().newInstance()
        when (animal) {
            is Predator -> cells[x][y].predators.add(animal)
            is Herbivore -> cells[x][y].herbivores.add(animal)
        }
    }

    fun <T : Animal> moveAnimal(animal: T, currentX: Int, currentY: Int, type: Class<T>) {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (currentX + dx).mod(width)
        val newY = (currentY + dy).mod(height)

        synchronized(cells[newX][newY]) {
            when (type) {
                Predator::class.java -> {
                    if (cells[newX][newY].predators.size < animal.maxPerCell) {
                        cells[currentX][currentY].predators.remove(animal as Predator)
                        cells[newX][newY].predators.add(animal)
                    }
                }
                Herbivore::class.java -> {
                    if (cells[newX][newY].herbivores.size < animal.maxPerCell) {
                        cells[currentX][currentY].herbivores.remove(animal as Herbivore)
                        cells[newX][newY].herbivores.add(animal)
                    }
                }
            }
        }
    }

    private fun updatePlants() {
        cells.forEach { row ->
            row.forEach { cell ->
                cell.plants.count = min(cell.plants.count + 10, 200)
            }
        }
    }

    private fun updateAnimals() {
        cells.flatten().forEach { cell ->
            taskExecutor.submit {
                processPredators(cell)
                processHerbivores(cell)
            }
        }
    }

    private fun processPredators(cell: Cell) {
        cell.predators.forEach { predator ->
            if (!predator.isAlive) return@forEach
            predator.eat(cell)
            val (x, y) = findCellCoordinates(cell)
            predator.move(x, y, this)
            val offspring = predator.reproduce()
            offspring?.let {
                if (cell.predators.size < predator.maxPerCell) {
                    cell.predators.add(offspring as Predator)
                }
            }
            predator.health -= 5
            if (predator.health <= 0) {
                cell.predators.remove(predator)
            }
        }
    }

    private fun processHerbivores(cell: Cell) {
        cell.herbivores.forEach { herbivore ->
            if (!herbivore.isAlive) return@forEach
            herbivore.eat(cell)
            val (x, y) = findCellCoordinates(cell)
            herbivore.move(x, y, this)
            val offspring = herbivore.reproduce()
            offspring?.let {
                if (cell.herbivores.size < herbivore.maxPerCell) {
                    cell.herbivores.add(offspring as Herbivore)
                }
            }
            herbivore.health -= 3
            if (herbivore.health <= 0) {
                cell.herbivores.remove(herbivore)
            }
        }
    }

    private fun findCellCoordinates(cell: Cell): Pair<Int, Int> {
        cells.forEachIndexed { x, row ->
            row.forEachIndexed { y, c ->
                if (c === cell) return x to y
            }
        }
        throw IllegalStateException("Cell not found")
    }

    private fun printStats() {
        val stats = cells.flatten().fold(Triple(0, 0, 0)) { acc, cell ->
            Triple(
                acc.first + cell.predators.size,
                acc.second + cell.herbivores.size,
                acc.third + cell.plants.count
            )
        }
        println("Stats: Predators=${stats.first}, Herbivores=${stats.second}, Plants=${stats.third}")
    }
}