fun main() {
    // Задача 1: Четное или нечетное число
    println("Задача 1: Четное или нечетное число")
    println("Введите целое число:")
    val number1 = readLine()!!.toInt()
    if (number1 % 2 == 0) {
        println("Число четное\n")
    } else {
        println("Число нечетное\n")
    }

    // Задача 2: Минимальное из трех чисел
    println("Задача 2: Минимальное из трех чисел")
    println("Введите три целых числа:")
    val num1 = readLine()!!.toInt()
    val num2 = readLine()!!.toInt()
    val num3 = readLine()!!.toInt()
    val min = minOf(num1, num2, num3)
    println("Минимальное число: $min\n")

    // Задача 3: Таблица умножения
    println("Задача 3: Таблица умножения")
    for (i in 1..10) {
        println("5 * $i = ${5 * i}")
    }
    println()

    // Задача 4: Сумма чисел от 1 до N
    println("Задача 4: Сумма чисел от 1 до N")
    println("Введите целое число N:")
    val n4 = readLine()!!.toInt()
    val sum4 = (1..n4).sum()
    println("Сумма чисел от 1 до $n4: $sum4\n")

    // Задача 5: Число Фибоначчи
    println("Задача 5: Число Фибоначчи")
    println("Введите целое число N:")
    val n5 = readLine()!!.toInt()
    val fibonacci = mutableListOf(0, 1)
    for (i in 2 until n5) {
        fibonacci.add(fibonacci[i - 1] + fibonacci[i - 2])
    }
    println("Первые $n5 чисел Фибоначчи: ${fibonacci.take(n5)}\n")

    // Задача 6: Проверка простого числа
    println("Задача 6: Проверка простого числа")
    println("Введите целое число:")
    val number6 = readLine()!!.toInt()
    if (isPrime(number6)) {
        println("Число простое\n")
    } else {
        println("Число не простое\n")
    }

    // Задача 7: Обратный порядок чисел
    println("Задача 7: Обратный порядок чисел")
    println("Введите целое число N:")
    val n7 = readLine()!!.toInt()
    for (i in n7 downTo 1) {
        println(i)
    }
    println()

    // Задача 8: Сумма четных чисел
    println("Задача 8: Сумма четных чисел")
    println("Введите два целых числа A и B:")
    val a8 = readLine()!!.toInt()
    val b8 = readLine()!!.toInt()
    val sum8 = (a8..b8).filter { it % 2 == 0 }.sum()
    println("Сумма четных чисел от $a8 до $b8: $sum8\n")

    // Задача 9: Реверс строки
    println("Задача 9: Реверс строки")
    println("Введите строку:")
    val input9 = readLine()!!
    val reversed9 = input9.reversed()
    println("Обратный порядок строки: $reversed9\n")

    // Задача 10: Количество цифр в числе
    println("Задача 10: Количество цифр в числе")
    println("Введите целое число:")
    val number10 = readLine()!!.toInt()
    val digitCount10 = number10.toString().length
    println("Количество цифр в числе: $digitCount10\n")

    // Задача 11: Факториал числа
    println("Задача 11: Факториал числа")
    println("Введите целое число N:")
    val n11 = readLine()!!.toInt()
    val factorial11 = (1..n11).reduce { acc, i -> acc * i }
    println("Факториал числа $n11: $factorial11\n")

    // Задача 12: Сумма цифр числа
    println("Задача 12: Сумма цифр числа")
    println("Введите целое число:")
    val number12 = readLine()!!.toInt()
    val sumOfDigits12 = number12.toString().map { it.digitToInt() }.sum()
    println("Сумма цифр числа: $sumOfDigits12\n")

    // Задача 13: Палиндром
    println("Задача 13: Палиндром")
    println("Введите строку:")
    val input13 = readLine()!!
    if (input13 == input13.reversed()) {
        println("Строка является палиндромом\n")
    } else {
        println("Строка не является палиндромом\n")
    }

    // Задача 14: Найти максимальное число в массиве
    println("Задача 14: Найти максимальное число в массиве")
    println("Введите размер массива:")
    val size14 = readLine()!!.toInt()
    val array14 = IntArray(size14) { readLine()!!.toInt() }
    val max14 = array14.maxOrNull()
    println("Максимальное число в массиве: $max14\n")

    // Задача 15: Сумма всех элементов массива
    println("Задача 15: Сумма всех элементов массива")
    println("Введите размер массива:")
    val size15 = readLine()!!.toInt()
    val array15 = IntArray(size15) { readLine()!!.toInt() }
    val sum15 = array15.sum()
    println("Сумма всех элементов массива: $sum15\n")

    // Задача 16: Количество положительных и отрицательных чисел
    println("Задача 16: Количество положительных и отрицательных чисел")
    println("Введите размер массива:")
    val size16 = readLine()!!.toInt()
    val array16 = IntArray(size16) { readLine()!!.toInt() }
    val positiveCount16 = array16.count { it > 0 }
    val negativeCount16 = array16.count { it < 0 }
    println("Количество положительных чисел: $positiveCount16")
    println("Количество отрицательных чисел: $negativeCount16\n")

    // Задача 17: Простые числа в диапазоне
    println("Задача 17: Простые числа в диапазоне")
    println("Введите два целых числа A и B:")
    val a17 = readLine()!!.toInt()
    val b17 = readLine()!!.toInt()
    val primes17 = (a17..b17).filter { isPrime(it) }
    println("Простые числа в диапазоне от $a17 до $b17: $primes17\n")

    // Задача 18: Подсчет гласных и согласных в строке
    println("Задача 18: Подсчет гласных и согласных в строке")
    println("Введите строку:")
    val input18 = readLine()!!
    val vowels18 = "aeiouAEIOU"
    val consonants18 = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ"
    val vowelCount18 = input18.count { it in vowels18 }
    val consonantCount18 = input18.count { it in consonants18 }
    println("Количество гласных: $vowelCount18")
    println("Количество согласных: $consonantCount18\n")

    // Задача 19: Перестановка слов в строке
    println("Задача 19: Перестановка слов в строке")
    println("Введите строку:")
    val input = readLine()!!
    val words = input.split(" ")
    val reversedWords = words.reversed().joinToString(" ")
    println("Слова в обратном порядке: $reversedWords")

    // Задача 20: Число Армстронга
    println("Задача 20: Число Армстронга")
    println("Введите целое число:")
    val number20 = readLine()!!.toInt()
    val digits20 = number20.toString().map { it.digitToInt() }
    val power20 = digits20.size
    val sumOfPowers20 = digits20.sumOf { Math.pow(it.toDouble(), power20.toDouble()).toInt() }
    if (sumOfPowers20 == number20) {
        println("Число является числом Армстронга\n")
    } else {
        println("Число не является числом Армстронга\n")
    }
}

fun isPrime(n: Int): Boolean {
    if (n <= 1) return false
    for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return false
    }
    return true
}
