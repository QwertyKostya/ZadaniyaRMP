class Bear : Predator(
    "Bear",
    500.0,
    5,
    2,
    80.0,
    mapOf("Rabbit" to 80, "Mouse" to 90, "Duck" to 10)
) {
    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].predators.size > maxPerCell / 2) {
            island.moveAnimal(this, currentX, currentY, Predator::class.java)
        }
    }
}