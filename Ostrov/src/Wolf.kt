class Wolf : Predator(
    "Wolf",
    50.0,
    30,
    3,
    8.0,
    mapOf("Rabbit" to 60, "Mouse" to 80, "Duck" to 40)
) {
    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].predators.size > maxPerCell) {
            island.moveAnimal(this, currentX, currentY, Predator::class.java)
        }
    }
}