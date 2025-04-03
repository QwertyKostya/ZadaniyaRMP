import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.random.Random

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
                    health = minOf(health + foodRequired, 100.0)
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