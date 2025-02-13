import java.io.File
import java.lang.Exception
import kotlin.math.abs

// Алфавиты
private val russianLower = ('а'..'я').toList()
private val russianUpper = ('А'..'Я').toList()
private val englishLower = ('a'..'z').toList()
private val englishUpper = ('A'..'Z').toList()

// Частоты символов для русского языка (примерные значения)
private val russianFrequencies = mapOf(
    'о' to 0.1097,
    'е' to 0.0845,
    'а' to 0.0801,
    'и' to 0.0735,
    'н' to 0.0670,
    'т' to 0.0626,
    'с' to 0.0547,
    'р' to 0.0473,
    'в' to 0.0454,
    'л' to 0.0440
)

fun main() {
    showMainMenu()
}

private fun showMainMenu() {
    while (true) {
        println("\nГлавное меню:")
        println("1. Шифрование файла")
        println("2. Расшифровка файла с ключом")
        println("3. Расшифровка методом Brute Force")
        println("4. Расшифровка статистическим анализом")
        println("5. Выход")
        print("Выберите опцию: ")

        when (readlnOrNull()?.toIntOrNull()) {
            1 -> handleEncryption()
            2 -> handleDecryption()
            3 -> handleBruteForce()
            4 -> handleStatisticalAnalysis()
            5 -> return
            else -> println("Некорректный ввод, попробуйте снова")
        }
    }
}

private fun handleEncryption() {
    try {
        print("Введите путь к исходному файлу: ")
        val inputFile = readln().trim()
        print("Введите путь для зашифрованного файла: ")
        val outputFile = readln().trim()
        print("Введите ключ шифрования (целое число): ")
        val key = readln().toInt()

        validateFile(inputFile)
        processFile(inputFile, outputFile, key, Operation.ENCRYPT)
        println("Шифрование успешно завершено!")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

private fun handleDecryption() {
    try {
        print("Введите путь к зашифрованному файлу: ")
        val inputFile = readln().trim()
        print("Введите путь для расшифрованного файла: ")
        val outputFile = readln().trim()
        print("Введите ключ дешифрования: ")
        val key = readln().toInt()

        validateFile(inputFile)
        processFile(inputFile, outputFile, key, Operation.DECRYPT)
        println("Дешифрование успешно завершено!")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

private fun handleBruteForce() {
    try {
        print("Введите путь к зашифрованному файлу: ")
        val inputFile = readln().trim()
        print("Введите путь для сохранения результатов: ")
        val outputDir = readln().trim()

        validateFile(inputFile)
        bruteForceDecrypt(inputFile, outputDir)
        println("Brute force атака завершена. Проверьте результаты в указанной директории.")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

private fun handleStatisticalAnalysis() {
    try {
        print("Введите путь к зашифрованному файлу: ")
        val inputFile = readln().trim()
        print("Введите путь для расшифрованного файла: ")
        val outputFile = readln().trim()

        validateFile(inputFile)
        statisticalDecrypt(inputFile, outputFile)
        println("Статистический анализ завершен. Результат сохранен.")
    } catch (e: Exception) {
        println("Ошибка: ${e.message}")
    }
}

private enum class Operation { ENCRYPT, DECRYPT }

private fun processFile(inputPath: String, outputPath: String, key: Int, operation: Operation) {
    File(inputPath).bufferedReader().use { reader ->
        File(outputPath).bufferedWriter().use { writer ->
            reader.forEachLine { line ->
                val processedLine = when (operation) {
                    Operation.ENCRYPT -> encrypt(line, key)
                    Operation.DECRYPT -> decrypt(line, key)
                }
                writer.write(processedLine)
                writer.newLine()
            }
        }
    }
}

private fun encrypt(text: String, shift: Int): String {
    return text.map { shiftChar(it, shift) }.joinToString("")
}

private fun decrypt(text: String, shift: Int): String {
    return text.map { shiftChar(it, -shift) }.joinToString("")
}

private fun shiftChar(c: Char, shift: Int): Char {
    val alphabet = getAlphabet(c) ?: return c
    val currentIndex = alphabet.indexOf(c)
    val shiftedIndex = (currentIndex + shift).mod(alphabet.size)
    return alphabet[shiftedIndex]
}

private fun getAlphabet(c: Char): List<Char>? {
    return when {
        c in englishLower -> englishLower
        c in englishUpper -> englishUpper
        c in russianLower -> russianLower
        c in russianUpper -> russianUpper
        else -> null
    }
}

private fun bruteForceDecrypt(inputPath: String, outputDir: String) {
    val maxAlphabetSize = listOf(
        englishLower.size,
        englishUpper.size,
        russianLower.size,
        russianUpper.size
    ).maxOrNull() ?: 26

    File(outputDir).mkdirs()

    repeat(maxAlphabetSize) { key ->
        val outputFile = File(outputDir, "brute_force_${key + 1}.txt")
        File(inputPath).bufferedReader().use { reader ->
            outputFile.bufferedWriter().use { writer ->
                reader.forEachLine { line ->
                    writer.write(decrypt(line, key + 1))
                    writer.newLine()
                }
            }
        }
    }
}

private fun statisticalDecrypt(inputPath: String, outputPath: String) {
    val encryptedText = File(inputPath).readText()
    val bestShift = findBestShift(encryptedText)
    processFile(inputPath, outputPath, bestShift, Operation.DECRYPT)
}

private fun findBestShift(text: String): Int {
    val textFrequencies = calculateFrequencies(text)
    val shifts = 1 until russianLower.size
    val scores = shifts.associateWith { shift ->
        calculateFrequencyMatchScore(textFrequencies, shift)
    }
    return scores.minByOrNull { it.value }?.key ?: 0
}

private fun calculateFrequencies(text: String): Map<Char, Double> {
    val filtered = text.filter { it in russianLower || it in russianUpper }
    if (filtered.isEmpty()) return emptyMap()

    val counts = filtered.groupingBy { it.lowercaseChar() }.eachCount()
    return counts.mapValues { (_, count) ->
        count.toDouble() / filtered.length
    }
}

private fun calculateFrequencyMatchScore(textFrequencies: Map<Char, Double>, shift: Int): Double {
    return textFrequencies.entries.sumOf { (char, observed) ->
        val originalChar = shiftChar(char, -shift).lowercaseChar()
        val expected = russianFrequencies[originalChar] ?: 0.0
        abs(observed - expected)
    }
}

private fun validateFile(path: String) {
    if (!File(path).exists()) {
        throw IllegalArgumentException("Файл $path не существует")
    }
}

private fun Char.lowercaseChar(): Char = this.lowercase().first()