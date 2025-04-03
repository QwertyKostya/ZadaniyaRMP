import java.util.concurrent.ConcurrentLinkedQueue

class Cell {
    val predators = ConcurrentLinkedQueue<Predator>()
    val herbivores = ConcurrentLinkedQueue<Herbivore>()
    val plants = Plant()
}