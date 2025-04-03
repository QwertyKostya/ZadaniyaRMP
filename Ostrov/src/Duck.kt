import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.random.Random

class Duck : Herbivore("Duck", 1.0, 200, 4, 0.15) {
    override fun eat(cell: Cell) {
        synchronized(cell) {
            val caterpillar = cell.herbivores.find { it.species == "Caterpillar" }
            if (caterpillar != null && Random.nextInt(100) < 90) {
                cell.herbivores.remove(caterpillar)
                health = minOf(health + foodRequired, 100.0)
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