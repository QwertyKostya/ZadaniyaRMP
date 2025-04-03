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