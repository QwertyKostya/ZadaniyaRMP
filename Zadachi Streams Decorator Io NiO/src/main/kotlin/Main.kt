import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.CompletionHandler
import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

// Задание 1: Работа с потоками ввода-вывода
fun copyFileWithUpperCase(inputFile: String, outputFile: String) {
    try {
        if (!Files.exists(Paths.get(inputFile))) {
            Files.write(Paths.get(inputFile), listOf("Hello, World!", "This is a test."))
        }
        BufferedReader(FileReader(inputFile)).use { reader ->
            BufferedWriter(FileWriter(outputFile)).use { writer ->
                reader.lineSequence().forEach { line ->
                    writer.write(line.uppercase())
                    writer.newLine()
                }
            }
        }
    } catch (e: IOException) {
        println("Ошибка при работе с файлом: ${e.message}")
    }
}

// Задание 2: Реализация паттерна Декоратор
interface TextProcessor {
    fun process(text: String): String
}

class SimpleTextProcessor : TextProcessor {
    override fun process(text: String): String {
        return text
    }
}

class UpperCaseDecorator(private val processor: TextProcessor) : TextProcessor {
    override fun process(text: String): String {
        return processor.process(text).uppercase()
    }
}

class TrimDecorator(private val processor: TextProcessor) : TextProcessor {
    override fun process(text: String): String {
        return processor.process(text).trim()
    }
}

class ReplaceDecorator(private val processor: TextProcessor) : TextProcessor {
    override fun process(text: String): String {
        return processor.process(text).replace(" ", "_")
    }
}

// Задание 3: Сравнение производительности IO и NIO
fun readFileWithIO(inputFile: String, outputFile: String) {
    try {
        if (!Files.exists(Paths.get(inputFile))) {
            Files.write(Paths.get(inputFile), listOf("This is a large file.", "It contains several lines of text."))
        }
        BufferedReader(FileReader(inputFile)).use { reader ->
            BufferedWriter(FileWriter(outputFile)).use { writer ->
                reader.lineSequence().forEach { line ->
                    writer.write(line)
                    writer.newLine()
                }
            }
        }
    } catch (e: IOException) {
        println("Ошибка при работе с файлом: ${e.message}")
    }
}

fun readFileWithNIO(inputFile: String, outputFile: String) {
    try {
        if (!Files.exists(Paths.get(inputFile))) {
            Files.write(Paths.get(inputFile), listOf("This is a large file.", "It contains several lines of text."))
        }
        FileChannel.open(Paths.get(inputFile), StandardOpenOption.READ).use { srcChannel ->
            FileChannel.open(Paths.get(outputFile), StandardOpenOption.WRITE, StandardOpenOption.CREATE).use { destChannel ->
                srcChannel.transferTo(0, srcChannel.size(), destChannel)
            }
        }
    } catch (e: IOException) {
        println("Ошибка при работе с файлом: ${e.message}")
    }
}

// Задание 4: Программа с использованием Java NIO
fun copyFileWithNIO(source: String, dest: String) {
    try {
        if (!Files.exists(Paths.get(source))) {
            Files.write(Paths.get(source), listOf("This is a large file.", "It contains several lines of text."))
        }
        FileChannel.open(Paths.get(source), StandardOpenOption.READ).use { srcChannel ->
            FileChannel.open(Paths.get(dest), StandardOpenOption.WRITE, StandardOpenOption.CREATE).use { destChannel ->
                srcChannel.transferTo(0, srcChannel.size(), destChannel)
            }
        }
    } catch (e: IOException) {
        println("Ошибка при копировании файла: ${e.message}")
    }
}

// Задание 5: Асинхронное чтение файла с использованием NIO.2
fun readFileAsync(filePath: String) {
    try {
        if (!Files.exists(Paths.get(filePath))) {
            Files.write(Paths.get(filePath), listOf("This is a large file.", "It contains several lines of text."))
        }
        AsynchronousFileChannel.open(Paths.get(filePath), StandardOpenOption.READ).use { channel ->
            val buffer = ByteBuffer.allocate(1024)
            channel.read(buffer, 0L, buffer, object : CompletionHandler<Int, ByteBuffer> {
                override fun completed(result: Int, attachment: ByteBuffer) {
                    if (result != -1) {
                        attachment.flip()
                        val data = ByteArray(attachment.remaining())
                        attachment.get(data)
                        println(String(data))
                        attachment.clear()
                        channel.read(attachment, 0L, attachment, this)
                    } else {
                        channel.close()
                    }
                }

                override fun failed(exc: Throwable, attachment: ByteBuffer) {
                    println("Ошибка при чтении файла: ${exc.message}")
                }
            })
        }
    } catch (e: Exception) {
        println("Ошибка при открытии файла: ${e.message}")
    }
}

fun main() {
    // Задание 1: Работа с потоками ввода-вывода
    copyFileWithUpperCase("input.txt", "output.txt")

    // Задание 2: Реализация паттерна Декоратор
    val processor: TextProcessor = ReplaceDecorator(
        UpperCaseDecorator(
            TrimDecorator(SimpleTextProcessor())
        )
    )
    val result = processor.process(" Hello world ")
    println(result) // Вывод: HELLO_WORLD

    // Задание 3: Сравнение производительности IO и NIO
    val inputFile = "largefile.txt"
    val outputFileIO = "output_io.txt"
    val outputFileNIO = "output_nio.txt"

    // Чтение с использованием IO
    val startTimeIO = System.currentTimeMillis()
    readFileWithIO(inputFile, outputFileIO)
    val endTimeIO = System.currentTimeMillis()
    println("Время выполнения IO: ${endTimeIO - startTimeIO} ms")

    // Чтение с использованием NIO
    val startTimeNIO = System.currentTimeMillis()
    readFileWithNIO(inputFile, outputFileNIO)
    val endTimeNIO = System.currentTimeMillis()
    println("Время выполнения NIO: ${endTimeNIO - startTimeNIO} ms")

    // Задание 4: Программа с использованием Java NIO
    val sourceFile = "largefile.txt"
    val destFile = "copied_largefile.txt"
    copyFileWithNIO(sourceFile, destFile)
    println("Файл скопирован успешно")

    // Задание 5: Асинхронное чтение файла с использованием NIO.2
    val filePath = "largefile.txt"
    readFileAsync(filePath)
}
