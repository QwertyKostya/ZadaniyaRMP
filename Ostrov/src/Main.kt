import java.util.concurrent.*
import kotlin.math.min
import kotlin.random.Random

// Абстрактный класс Animal
abstract class Animal(
    val species: String,
    val weight: Double,
    val maxPerCell: Int,
    val speed: Int,
    val foodRequired: Double
) {
    var health: Double = 100.0
    var isAlive: Boolean = true

    abstract fun eat(cell: Cell)
    abstract fun move(currentX: Int, currentY: Int, island: Island)
    abstract fun reproduce(): Animal?
}

// Базовый класс для хищников
abstract class Predator(
    species: String,
    weight: Double,
    maxPerCell: Int,
    speed: Int,
    foodRequired: Double,
    private val preyProbabilities: Map<String, Int>
) : Animal(species, weight, maxPerCell, speed, foodRequired) {

    override fun eat(cell: Cell) {
        synchronized(cell) {
            val prey = cell.herbivores.filter { preyProbabilities.containsKey(it.species) }
                .minByOrNull { it.health }

            prey?.let {
                if (Random.nextInt(100) < preyProbabilities[it.species]!!) {
                    cell.herbivores.remove(it)
                    health = min(health + foodRequired, 100.0)
                }
            }
        }
    }

    override fun reproduce(): Animal? {
        return if (health > 80 && Random.nextDouble() < 0.3) {
            this::class.java.getDeclaredConstructor().newInstance()
        } else null
    }
}

// Базовый класс для травоядных
abstract class Herbivore(
    species: String,
    weight: Double,
    maxPerCell: Int,
    speed: Int,
    foodRequired: Double
) : Animal(species, weight, maxPerCell, speed, foodRequired) {

    override fun eat(cell: Cell) {
        synchronized(cell) {
            val plantsToEat = min(cell.plants.count, 3)
            cell.plants.count -= plantsToEat
            health = min(health + (plantsToEat * 0.5), 100.0)
        }
    }

    override fun reproduce(): Animal? {
        return if (health > 70 && Random.nextDouble() < 0.4) {
            this::class.java.getDeclaredConstructor().newInstance()
        } else null
    }
}

// Конкретные классы хищников
class Wolf : Predator("Wolf", 50.0, 30, 3, 8.0,
    mapOf("Rabbit" to 60, "Mouse" to 80, "Duck" to 40)) {

    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].predators.size > maxPerCell) {
            island.moveAnimal(this, currentX, currentY, Predator::class.java)
        }
    }
}

class Bear : Predator("Bear", 500.0, 5, 2, 80.0,
    mapOf("Rabbit" to 80, "Mouse" to 90, "Duck" to 10)) {

    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].predators.size > maxPerCell / 2) {
            island.moveAnimal(this, currentX, currentY, Predator::class.java)
        }
    }
}

// Конкретные классы травоядных
class Rabbit : Herbivore("Rabbit", 2.0, 150, 2, 0.45) {
    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].herbivores.size > maxPerCell) {
            island.moveAnimal(this, currentX, currentY, Herbivore::class.java)
        }
    }
}

class Duck : Herbivore("Duck", 1.0, 200, 4, 0.15) {
    override fun eat(cell: Cell) {
        synchronized(cell) {
            val caterpillar = cell.herbivores.find { it.species == "Caterpillar" }
            if (caterpillar != null && Random.nextInt(100) < 90) {
                cell.herbivores.remove(caterpillar)
                health = min(health + foodRequired, 100.0)
            } else {
                super.eat(cell)
            }
        }
    }

    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].herbivores.size > maxPerCell) {
            island.moveAnimal(this, currentX, currentY, Herbivore::class.java)
        }
    }
}

// Класс растения
data class Plant(var count: Int = 0)

// Класс клетки острова
class Cell {
    val predators = ConcurrentLinkedQueue<Predator>()
    val herbivores = ConcurrentLinkedQueue<Herbivore>()
    val plants = Plant()
}

// Класс острова
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
        // Инициализация начального количества животных
        repeat(50) { addRandomAnimal(Wolf::class.java) }
        repeat(30) { addRandomAnimal(Rabbit::class.java) }
        // Аналогично для других видов...
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

    fun <T : Animal> moveAnimal(
        animal: T,
        currentX: Int,
        currentY: Int,
        type: Class<T>
    ) {
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

fun main() {
    val island = Island(100, 20)
    island.startSimulation()
}