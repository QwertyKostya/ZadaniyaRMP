import java.util.*

// Задача 1: Создание класса базы данных
object DatabaseConnection {
    init {
        println("Создано подключение к базе данных")
    }

    fun connect() {
        println("Подключение к базе данных установлено")
    }
}

// Задача 2: Логирование в системе
object Logger {
    private val logs = mutableListOf<String>()

    fun log(message: String) {
        logs.add(message)
    }

    fun printLogs() {
        logs.forEach { println(it) }
    }
}

// Задача 3: Реализация статусов заказа
enum class OrderStatus {
    NEW, IN_PROGRESS, DELIVERED, CANCELLED
}

class Order(private var status: OrderStatus) {

    fun changeStatus(newStatus: OrderStatus) {
        when (status) {
            OrderStatus.NEW -> {
                if (newStatus == OrderStatus.IN_PROGRESS || newStatus == OrderStatus.CANCELLED) {
                    status = newStatus
                }
            }
            OrderStatus.IN_PROGRESS -> {
                if (newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELLED) {
                    status = newStatus
                }
            }
            OrderStatus.DELIVERED -> {
                // Нельзя отменить доставленный заказ
            }
            OrderStatus.CANCELLED -> {
                // Нельзя изменить статус отмененного заказа
            }
        }
    }

    fun getStatus(): OrderStatus {
        return status
    }
}

// Задача 4: Сезоны года
enum class Season {
    WINTER, SPRING, SUMMER, AUTUMN
}

fun getSeasonName(season: Season): String {
    return when (season) {
        Season.WINTER -> "Зима"
        Season.SPRING -> "Весна"
        Season.SUMMER -> "Лето"
        Season.AUTUMN -> "Осень"
    }
}

fun main() {
    // Задача 1: Проверка Singleton для базы данных
    val db1 = DatabaseConnection
    val db2 = DatabaseConnection

    db1.connect()
    db2.connect()

    println(db1 === db2) // true, так как это один и тот же объект

    // Задача 2: Логирование в системе
    Logger.log("Первое сообщение")
    Logger.log("Второе сообщение")

    Logger.printLogs()

    // Задача 3: Реализация статусов заказа
    val order = Order(OrderStatus.NEW)
    println(order.getStatus()) // NEW

    order.changeStatus(OrderStatus.IN_PROGRESS)
    println(order.getStatus()) // IN_PROGRESS

    order.changeStatus(OrderStatus.DELIVERED)
    println(order.getStatus()) // DELIVERED

    order.changeStatus(OrderStatus.CANCELLED)
    println(order.getStatus()) // DELIVERED (не изменился)

    // Задача 4: Сезоны года
    println(getSeasonName(Season.WINTER)) // Зима
    println(getSeasonName(Season.SPRING)) // Весна
    println(getSeasonName(Season.SUMMER)) // Лето
    println(getSeasonName(Season.AUTUMN)) // Осень
}
