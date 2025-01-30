import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

fun main() {
    // 1. Основы LocalDate и LocalTime
    val currentDateTime = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    val dateTimeString = currentDateTime.format(formatter)
    println(dateTimeString)

    // 2. Сравнение дат
    val date1 = LocalDate.of(2023, 10, 1)
    val date2 = LocalDate.of(2023, 10, 5)
    println(compareDates(date1, date2))

    // 3. Сколько дней до Нового года?
    println("Дней до Нового года: ${daysUntilNewYear()}")

    // 4. Проверка високосного года
    println("2024 високосный год: ${isLeapYear(2024)}")

    // 5. Подсчет выходных за месяц
    println("Выходных в октябре 2023: ${countWeekendsInMonth(10, 2023)}")

    // 6. Расчет времени выполнения метода
    println("Время выполнения метода: ${measureExecutionTime { longRunningMethod() }} ms")

    // 7. Форматирование и парсинг даты
    val dateString = "01-10-2023"
    println("Дата через 10 дней: ${formatAndParseDate(dateString)}")

    // 8. Конвертация между часовыми поясами
    val utcDateTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"))
    println("Время в Москве: ${convertToTimeZone(utcDateTime, "Europe/Moscow")}")

    // 9. Вычисление возраста по дате рождения
    val birthDate = LocalDate.of(1990, 1, 1)
    println("Возраст: ${calculateAge(birthDate)}")

    // 10. Создание календаря на месяц
    printCalendar(10, 2023)

    // 11. Генерация случайной даты в диапазоне
    val startDate = LocalDate.of(2023, 1, 1)
    val endDate = LocalDate.of(2023, 12, 31)
    println("Случайная дата: ${generateRandomDate(startDate, endDate)}")

    // 12. Расчет времени до заданной даты
    val eventDateTime = LocalDateTime.of(2023, 12, 31, 23, 59, 59)
    println("Время до события: ${calculateTimeUntilEvent(eventDateTime)}")

    // 13. Вычисление количества рабочих часов
    val startWork = LocalDateTime.of(2023, 10, 1, 9, 0)
    val endWork = LocalDateTime.of(2023, 10, 1, 17, 0)
    println("Рабочих часов: ${calculateWorkingHours(startWork, endWork)}")

    // 14. Конвертация даты в строку с учетом локали
    val localDate = LocalDate.of(2023, 10, 1)
    println("Дата на русском: ${formatDateWithLocale(localDate, Locale("ru"))}")

    // 15. Определение дня недели по дате
    val someDate = LocalDate.of(2023, 10, 1)
    println("День недели: ${getDayOfWeek(someDate)}")
}

// 2. Сравнение дат
fun compareDates(date1: LocalDate, date2: LocalDate): String {
    return when {
        date1.isBefore(date2) -> "Первая дата меньше второй"
        date1.isAfter(date2) -> "Первая дата больше второй"
        else -> "Даты равны"
    }
}

// 3. Сколько дней до Нового года?
fun daysUntilNewYear(): Long {
    val today = LocalDate.now()
    val newYear = LocalDate.of(today.year + 1, 1, 1)
    return ChronoUnit.DAYS.between(today, newYear)
}

// 4. Проверка високосного года
fun isLeapYear(year: Int): Boolean {
    return Year.of(year).isLeap
}

// 5. Подсчет выходных за месяц
fun countWeekendsInMonth(month: Int, year: Int): Int {
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth())
    var weekendCount = 0
    var currentDay = firstDayOfMonth
    while (currentDay.isBefore(lastDayOfMonth.plusDays(1))) {
        if (currentDay.dayOfWeek == DayOfWeek.SATURDAY || currentDay.dayOfWeek == DayOfWeek.SUNDAY) {
            weekendCount++
        }
        currentDay = currentDay.plusDays(1)
    }
    return weekendCount
}

// 6. Расчет времени выполнения метода
fun measureExecutionTime(action: () -> Unit): Long {
    val startTime = System.currentTimeMillis()
    action()
    val endTime = System.currentTimeMillis()
    return endTime - startTime
}

fun longRunningMethod() {
    for (i in 1..1_000_000) {
        // Симуляция долгой работы
    }
}

// 7. Форматирование и парсинг даты
fun formatAndParseDate(dateString: String): String {
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val localDate = LocalDate.parse(dateString, formatter)
    val newDate = localDate.plusDays(10)
    return newDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}

// 8. Конвертация между часовыми поясами
fun convertToTimeZone(utcDateTime: ZonedDateTime, targetTimeZone: String): String {
    val targetDateTime = utcDateTime.withZoneSameInstant(ZoneId.of(targetTimeZone))
    return targetDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

// 9. Вычисление возраста по дате рождения
fun calculateAge(birthDate: LocalDate): Int {
    val today = LocalDate.now()
    return ChronoUnit.YEARS.between(birthDate, today).toInt()
}

// 10. Создание календаря на месяц
fun printCalendar(month: Int, year: Int) {
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val lastDayOfMonth = firstDayOfMonth.withDayOfMonth(firstDayOfMonth.lengthOfMonth())
    var currentDay = firstDayOfMonth
    while (currentDay.isBefore(lastDayOfMonth.plusDays(1))) {
        val dayOfWeek = currentDay.dayOfWeek
        val dayType = if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) "Выходной" else "Рабочий"
        println("$currentDay - $dayType")
        currentDay = currentDay.plusDays(1)
    }
}

// 11. Генерация случайной даты в диапазоне
fun generateRandomDate(startDate: LocalDate, endDate: LocalDate): LocalDate {
    val startEpochDay = startDate.toEpochDay()
    val endEpochDay = endDate.toEpochDay()
    val randomDay = Random().nextLong(startEpochDay, endEpochDay)
    return LocalDate.ofEpochDay(randomDay)
}

// 12. Расчет времени до заданной даты
fun calculateTimeUntilEvent(eventDateTime: LocalDateTime): String {
    val now = LocalDateTime.now()
    val duration = Duration.between(now, eventDateTime)
    val hours = duration.toHours()
    val minutes = duration.toMinutes() % 60
    val seconds = duration.seconds % 60
    return "$hours часов, $minutes минут, $seconds секунд"
}

// 13. Вычисление количества рабочих часов
fun calculateWorkingHours(start: LocalDateTime, end: LocalDateTime): Long {
    val duration = Duration.between(start, end)
    return duration.toHours()
}

// 14. Конвертация даты в строку с учетом локали
fun formatDateWithLocale(date: LocalDate, locale: Locale): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", locale)
    return date.format(formatter)
}

// 15. Определение дня недели по дате
fun getDayOfWeek(date: LocalDate): String {
    val dayOfWeek = date.dayOfWeek
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "Понедельник"
        DayOfWeek.TUESDAY -> "Вторник"
        DayOfWeek.WEDNESDAY -> "Среда"
        DayOfWeek.THURSDAY -> "Четверг"
        DayOfWeek.FRIDAY -> "Пятница"
        DayOfWeek.SATURDAY -> "Суббота"
        DayOfWeek.SUNDAY -> "Воскресенье"
    }
}
