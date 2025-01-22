import java.util.*
import kotlin.math.sqrt

// 1. Класс "Человек"
open class Person(var name: String, var age: Int, var gender: String) {
    fun displayInfo() {
        println("Имя: $name, Возраст: $age, Пол: $gender")
    }

    fun increaseAge() {
        age++
    }

    fun changeName(newName: String) {
        name = newName
    }
}

// 2. Наследование: Класс "Работник" и "Менеджер"
open class Worker(name: String, age: Int, gender: String, var salary: Double) : Person(name, age, gender)

class Manager(name: String, age: Int, gender: String, salary: Double) : Worker(name, age, gender, salary) {
    val subordinates = mutableListOf<Worker>()

    fun addSubordinate(worker: Worker) {
        subordinates.add(worker)
    }
}

// 3. Полиморфизм: Животные
interface Animal {
    fun makeSound()
}

class Dog : Animal {
    override fun makeSound() {
        println("Гав-гав")
    }
}

class Cat : Animal {
    override fun makeSound() {
        println("Мяу")
    }
}

class Cow : Animal {
    override fun makeSound() {
        println("Муу")
    }
}

// 4. Абстрактный класс "Транспорт"
abstract class Transport {
    abstract fun move()
}

class Car : Transport() {
    override fun move() {
        println("Автомобиль едет")
    }
}

class Bike : Transport() {
    override fun move() {
        println("Велосипед едет")
    }
}

// 5. Класс "Книга" и "Библиотека"
class Book(val title: String, val author: String, val year: Int)

class Library {
    private val books = mutableListOf<Book>()

    fun addBook(book: Book) {
        books.add(book)
    }

    fun findBooksByAuthor(author: String): List<Book> {
        return books.filter { it.author == author }
    }

    fun findBooksByYear(year: Int): List<Book> {
        return books.filter { it.year == year }
    }
}

// 6. Инкапсуляция: Банк
class BankAccount(private val accountNumber: String, private var balance: Double) {
    fun deposit(amount: Double) {
        if (amount > 0) {
            balance += amount
        }
    }

    fun withdraw(amount: Double) {
        if (amount > 0 && amount <= balance) {
            balance -= amount
        }
    }

    fun getBalance(): Double {
        return balance
    }
}

// 7. Счетчик объектов
class Counter {
    companion object {
        private var count = 0

        fun increment() {
            count++
        }

        fun getCount(): Int {
            return count
        }
    }

    init {
        increment()
    }
}

// 8. Площадь фигур
abstract class Shape {
    abstract fun getArea(): Double
}

class Circle(private val radius: Double) : Shape() {
    override fun getArea(): Double {
        return Math.PI * radius * radius
    }
}

class Rectangle(private val width: Double, private val height: Double) : Shape() {
    override fun getArea(): Double {
        return width * height
    }
}

// 9. Наследование: Животные и их движения
open class AnimalMovement {
    open fun move() {
        println("Животное двигается")
    }
}

class Fish : AnimalMovement() {
    override fun move() {
        println("Рыба плавает")
    }
}

class Bird : AnimalMovement() {
    override fun move() {
        println("Птица летает")
    }
}

class DogMovement : AnimalMovement() {
    override fun move() {
        println("Собака бегает")
    }
}

// 10. Работа с коллекциями: Университет
class Student(val name: String, val group: String, val grade: Double)

class University {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun sortStudentsByName(): List<Student> {
        return students.sortedBy { it.name }
    }

    fun filterStudentsByGrade(minGrade: Double): List<Student> {
        return students.filter { it.grade >= minGrade }
    }
}

// 11. Класс "Магазин"
class Product(val name: String, val price: Double, var quantity: Int)

class Store {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(productName: String) {
        products.removeIf { it.name == productName }
    }

    fun findProductByName(productName: String): Product? {
        return products.find { it.name == productName }
    }
}

// 12. Интерфейс "Платежная система"
interface PaymentSystem {
    fun pay(amount: Double)
    fun refund(amount: Double)
}

class CreditCard : PaymentSystem {
    override fun pay(amount: Double) {
        println("Оплата $amount с помощью кредитной карты")
    }

    override fun refund(amount: Double) {
        println("Возврат $amount на кредитную карту")
    }
}

class PayPal : PaymentSystem {
    override fun pay(amount: Double) {
        println("Оплата $amount с помощью PayPal")
    }

    override fun refund(amount: Double) {
        println("Возврат $amount на PayPal")
    }
}

// 13. Генерация уникальных идентификаторов
class UniqueID {
    companion object {
        private var idCounter = 0

        fun generateID(): Int {
            return idCounter++
        }
    }
}

// 14. Класс "Точка" и "Прямоугольник"
class Point(val x: Int, val y: Int)

class RectangleShape(private val topLeft: Point, private val bottomRight: Point) {
    fun getArea(): Int {
        val width = bottomRight.x - topLeft.x
        val height = topLeft.y - bottomRight.y
        return width * height
    }
}

// 15. Комплексные числа
class ComplexNumber(private val real: Double, private val imaginary: Double) {
    fun add(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(real + other.real, imaginary + other.imaginary)
    }

    fun subtract(other: ComplexNumber): ComplexNumber {
        return ComplexNumber(real - other.real, imaginary - other.imaginary)
    }

    fun multiply(other: ComplexNumber): ComplexNumber {
        val realPart = real * other.real - imaginary * other.imaginary
        val imaginaryPart = real * other.imaginary + imaginary * other.real
        return ComplexNumber(realPart, imaginaryPart)
    }

    fun divide(other: ComplexNumber): ComplexNumber {
        val denominator = other.real * other.real + other.imaginary * other.imaginary
        val realPart = (real * other.real + imaginary * other.imaginary) / denominator
        val imaginaryPart = (imaginary * other.real - real * other.imaginary) / denominator
        return ComplexNumber(realPart, imaginaryPart)
    }

    override fun toString(): String {
        return "$real + ${imaginary}i"
    }
}

// 16. Перегрузка операторов: Матрица
class Matrix(private val data: Array<Array<Int>>) {
    operator fun plus(other: Matrix): Matrix {
        val result = Array(data.size) { Array(data[0].size) { 0 } }
        for (i in data.indices) {
            for (j in data[i].indices) {
                result[i][j] = data[i][j] + other.data[i][j]
            }
        }
        return Matrix(result)
    }

    operator fun times(other: Matrix): Matrix {
        val result = Array(data.size) { Array(other.data[0].size) { 0 } }
        for (i in data.indices) {
            for (j in other.data[0].indices) {
                for (k in other.data.indices) {
                    result[i][j] += data[i][k] * other.data[k][j]
                }
            }
        }
        return Matrix(result)
    }

    override fun toString(): String {
        return data.joinToString("\n") { it.joinToString(" ") }
    }
}

// 17. Создание игры с использованием ООП
class Player(val name: String, var health: Int) {
    fun attack(enemy: Enemy) {
        enemy.takeDamage(10)
    }
}

class Enemy(val name: String, var health: Int) {
    fun takeDamage(damage: Int) {
        health -= damage
        println("$name получил $damage урона. Осталось здоровья: $health")
    }
}

class Weapon(val name: String, val damage: Int)

// 18. Автоматизированная система заказов
class Order(val customer: Customer, val products: List<Product>) {
    fun getTotalCost(): Double {
        return products.sumOf { it.price * it.quantity }
    }
}

class Customer(val name: String, val address: String)

// 19. Наследование: Электроника
open class Device(val brand: String) {
    fun turnOn() {
        println("$brand устройство включено")
    }

    fun turnOff() {
        println("$brand устройство выключено")
    }
}

class Smartphone(brand: String) : Device(brand) {
    fun takePhoto() {
        println("Сделан снимок на $brand смартфон")
    }
}

class Laptop(brand: String) : Device(brand) {
    fun code() {
        println("Пишем код на $brand ноутбуке")
    }
}

// 20. Игра "Крестики-нолики"
class TicTacToeGame {
    private val board = Array(3) { Array(3) { ' ' } }
    private var currentPlayer = 'X'

    fun makeMove(row: Int, col: Int) {
        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer
            currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
        } else {
            println("Неверный ход")
        }
    }

    fun displayBoard() {
        for (row in board) {
            println(row.joinToString("|"))
        }
    }

    fun checkWinner(): Char? {
        // Проверка строк
        for (row in board) {
            if (row[0] != ' ' && row.all { it == row[0] }) {
                return row[0]
            }
        }
        // Проверка столбцов
        for (col in 0 until 3) {
            if (board[0][col] != ' ' && (0 until 3).all { board[it][col] == board[0][col] }) {
                return board[0][col]
            }
        }
        // Проверка диагоналей
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0]
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2]
        }
        return null
    }
}

fun main() {
    // Примеры использования классов

    // 1. Класс "Человек"
    val person = Person("Иван", 30, "Мужской")
    person.displayInfo()
    person.increaseAge()
    person.changeName("Петр")
    person.displayInfo()
    println()

    // 2. Наследование: Класс "Работник" и "Менеджер"
    val manager = Manager("Анна", 40, "Женский", 50000.0)
    val worker = Worker("Борис", 35, "Мужской", 30000.0)
    manager.addSubordinate(worker)
    println("Менеджер: ${manager.name}, Подчиненные: ${manager.subordinates.size}")
    println()

    // 3. Полиморфизм: Животные
    val animals: List<Animal> = listOf(Dog(), Cat(), Cow())
    for (animal in animals) {
        animal.makeSound()
    }
    println()

    // 4. Абстрактный класс "Транспорт"
    val car = Car()
    val bike = Bike()
    car.move()
    bike.move()
    println()

    // 5. Класс "Книга" и "Библиотека"
    val library = Library()
    library.addBook(Book("1984", "Джордж Оруэлл", 1949))
    library.addBook(Book("Мастер и Маргарита", "Михаил Булгаков", 1967))
    println("Книги автора 'Михаил Булгаков': ${library.findBooksByAuthor("Михаил Булгаков")}")
    println("Книги, изданные в 1949 году: ${library.findBooksByYear(1949)}")
    println()

    // 6. Инкапсуляция: Банк
    val account = BankAccount("123456789", 1000.0)
    account.deposit(500.0)
    account.withdraw(200.0)
    println("Баланс: ${account.getBalance()}")
    println()

    // 7. Счетчик объектов
    val counter1 = Counter()
    val counter2 = Counter()
    println("Количество объектов Counter: ${Counter.getCount()}")
    println()

    // 8. Площадь фигур
    val circle = Circle(5.0)
    val rectangle = Rectangle(4.0, 6.0)
    println("Площадь круга: ${circle.getArea()}")
    println("Площадь прямоугольника: ${rectangle.getArea()}")
    println()

    // 9. Наследование: Животные и их движения
    val fish = Fish()
    val bird = Bird()
    val dogMovement = DogMovement()
    fish.move()
    bird.move()
    dogMovement.move()
    println()

    // 10. Работа с коллекциями: Университет
    val university = University()
    university.addStudent(Student("Алексей", "Группа 1", 4.5))
    university.addStudent(Student("Мария", "Группа 2", 3.8))
    println("Студенты, отсортированные по имени: ${university.sortStudentsByName()}")
    println("Студенты с оценкой выше 4.0: ${university.filterStudentsByGrade(4.0)}")
    println()

    // 11. Класс "Магазин"
    val store = Store()
    store.addProduct(Product("Яблоко", 1.0, 10))
    store.addProduct(Product("Банан", 1.5, 5))
    println("Продукт 'Яблоко': ${store.findProductByName("Яблоко")}")
    store.removeProduct("Банан")
    println("Продукт 'Банан': ${store.findProductByName("Банан")}")
    println()

    // 12. Интерфейс "Платежная система"
    val creditCard = CreditCard()
    val payPal = PayPal()
    creditCard.pay(100.0)
    payPal.refund(50.0)
    println()

    // 13. Генерация уникальных идентификаторов
    val id1 = UniqueID.generateID()
    val id2 = UniqueID.generateID()
    println("Уникальные идентификаторы: $id1, $id2")
    println()

    // 14. Класс "Точка" и "Прямоугольник"
    val topLeft = Point(0, 10)
    val bottomRight = Point(10, 0)
    val rectangleShape = RectangleShape(topLeft, bottomRight)
    println("Площадь прямоугольника: ${rectangleShape.getArea()}")
    println()

    // 15. Комплексные числа
    val complex1 = ComplexNumber(3.0, 2.0)
    val complex2 = ComplexNumber(1.0, 7.0)
    println("Сложение: ${complex1.add(complex2)}")
    println("Вычитание: ${complex1.subtract(complex2)}")
    println("Умножение: ${complex1.multiply(complex2)}")
    println("Деление: ${complex1.divide(complex2)}")
    println()

    // 16. Перегрузка операторов: Матрица
    val matrix1 = Matrix(arrayOf(arrayOf(1, 2), arrayOf(3, 4)))
    val matrix2 = Matrix(arrayOf(arrayOf(5, 6), arrayOf(7, 8)))
    println("Сложение матриц:\n${matrix1 + matrix2}")
    println("Умножение матриц:\n${matrix1 * matrix2}")
    println()

    // 17. Создание игры с использованием ООП
    val player = Player("Игрок", 100)
    val enemy = Enemy("Враг", 50)
    player.attack(enemy)
    println()

    // 18. Автоматизированная система заказов
    val customer = Customer("Иван", "ул. Ленина, 1")
    val order = Order(customer, listOf(Product("Яблоко", 1.0, 10), Product("Банан", 1.5, 5)))
    println("Общая стоимость заказа: ${order.getTotalCost()}")
    println()

    // 19. Наследование: Электроника
    val smartphone = Smartphone("Apple")
    val laptop = Laptop("Dell")
    smartphone.turnOn()
    smartphone.takePhoto()
    smartphone.turnOff()
    laptop.turnOn()
    laptop.code()
    laptop.turnOff()
    println()

    // 20. Игра "Крестики-нолики"
    val game = TicTacToeGame()
    game.makeMove(0, 0)
    game.makeMove(1, 1)
    game.makeMove(0, 1)
    game.makeMove(1, 0)
    game.makeMove(0, 2)
    game.displayBoard()
    println("Победитель: ${game.checkWinner()}")
}
