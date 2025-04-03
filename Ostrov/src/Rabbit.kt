class Rabbit : Herbivore("Rabbit", 2.0, 150, 2, 0.45) {
    override fun move(currentX: Int, currentY: Int, island: Island) {
        if (island.cells[currentX][currentY].herbivores.size > maxPerCell) {
            island.moveAnimal(this, currentX, currentY, Herbivore::class.java)
        }
    }
}