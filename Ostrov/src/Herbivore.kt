import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.random.Random

abstract class Herbivore(
    species: String,
    weight: Double,
    maxPerCell: Int,
    speed: Int,
    foodRequired: Double
) : Animal(species, weight, maxPerCell, speed, foodRequired) {

    override fun eat(cell: Cell) {
        synchronized(cell) {
            val plantsToEat = minOf(cell.plants.count, 3)
            cell.plants.count -= plantsToEat
            health = minOf(health + (plantsToEat * 0.5), 100.0)
        }
    }

    override fun reproduce(): Animal? {
        return if (health > 70 && Random.nextDouble() < 0.4) {
            this::class.java.getDeclaredConstructor().newInstance()
        } else null
    }
}