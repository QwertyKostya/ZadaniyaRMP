import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

class WeakPasswordException extends Exception {
    public WeakPasswordException(String message) {
        super(message);
    }
}

public class Main {
    public static void main(String[] args) {
        // Задача 1: Функция для нахождения максимума
        System.out.println("Задача 1: Функция для нахождения максимума");
        try {
            System.out.println("Максимальное число: " + max(5, 10));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 2: Калькулятор деления
        System.out.println("Задача 2: Калькулятор деления");
        try {
            System.out.println("Результат деления: " + divide(10, 2));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 3: Конвертер строк в числа
        System.out.println("Задача 3: Конвертер строк в числа");
        try {
            System.out.println("Конвертированное число: " + stringToInt("123"));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 4: Проверка возраста
        System.out.println("Задача 4: Проверка возраста");
        try {
            checkAge(25);
            System.out.println("Возраст корректен");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 5: Нахождение корня
        System.out.println("Задача 5: Нахождение корня");
        try {
            System.out.println("Корень числа: " + sqrt(16));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 6: Факториал
        System.out.println("Задача 6: Факториал");
        try {
            System.out.println("Факториал числа: " + factorial(5));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 7: Проверка массива на нули
        System.out.println("Задача 7: Проверка массива на нули");
        try {
            checkArrayForZeros(new int[]{1, 2, 3});
            System.out.println("Массив не содержит нулей");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 8: Калькулятор возведения в степень
        System.out.println("Задача 8: Калькулятор возведения в степень");
        try {
            System.out.println("Результат возведения в степень: " + power(2, 3));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 9: Обрезка строки
        System.out.println("Задача 9: Обрезка строки");
        try {
            System.out.println("Обрезанная строка: " + truncateString("Hello, World!", 5));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 10: Поиск элемента в массиве
        System.out.println("Задача 10: Поиск элемента в массиве");
        try {
            System.out.println("Индекс элемента: " + findElement(new int[]{1, 2, 3, 4, 5}, 3));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 11: Конвертация в двоичную систему
        System.out.println("Задача 11: Конвертация в двоичную систему");
        try {
            System.out.println("Двоичное представление: " + toBinaryString(10));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 12: Проверка делимости
        System.out.println("Задача 12: Проверка делимости");
        try {
            System.out.println("Делится ли число: " + isDivisible(10, 2));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 13: Чтение элемента списка
        System.out.println("Задача 13: Чтение элемента списка");
        try {
            System.out.println("Элемент списка: " + getListElement(List.of(1, 2, 3, 4, 5), 2));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 14: Парольная проверка
        System.out.println("Задача 14: Парольная проверка");
        try {
            checkPassword("password123");
            System.out.println("Пароль корректен");
        } catch (WeakPasswordException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 15: Проверка даты
        System.out.println("Задача 15: Проверка даты");
        try {
            System.out.println("Дата корректна: " + isValidDate("25.12.2023"));
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 16: Конкатенация строк
        System.out.println("Задача 16: Конкатенация строк");
        try {
            System.out.println("Объединенная строка: " + concatStrings("Hello", "World"));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 17: Остаток от деления
        System.out.println("Задача 17: Остаток от деления");
        try {
            System.out.println("Остаток от деления: " + modulus(10, 3));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 18: Квадратный корень
        System.out.println("Задача 18: Квадратный корень");
        try {
            System.out.println("Квадратный корень: " + sqrt(16));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 19: Конвертер температуры
        System.out.println("Задача 19: Конвертер температуры");
        try {
            System.out.println("Температура в Фаренгейтах: " + celsiusToFahrenheit(25));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

        // Задача 20: Проверка строки на пустоту
        System.out.println("Задача 20: Проверка строки на пустоту");
        try {
            checkStringForEmptyOrNull("Hello");
            System.out.println("Строка корректна");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    // 1. Функция для нахождения максимума
    public static int max(int a, int b) throws Exception {
        if (a == b) {
            throw new Exception("Числа равны");
        }
        return Math.max(a, b);
    }

    // 2. Калькулятор деления
    public static double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return (double) a / b;
    }

    // 3. Конвертер строк в числа
    public static int stringToInt(String str) {
        return Integer.parseInt(str);
    }

    // 4. Проверка возраста
    public static void checkAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Недопустимый возраст");
        }
    }

    // 5. Нахождение корня
    public static double sqrt(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Недопустимое число для извлечения корня");
        }
        return Math.sqrt(number);
    }

    // 6. Факториал
    public static int factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Недопустимое число для вычисления факториала");
        }
        int result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    // 7. Проверка массива на нули
    public static void checkArrayForZeros(int[] array) {
        for (int num : array) {
            if (num == 0) {
                throw new IllegalArgumentException("Массив содержит нули");
            }
        }
    }

    // 8. Калькулятор возведения в степень
    public static double power(int base, int exponent) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Недопустимая степень");
        }
        return Math.pow(base, exponent);
    }

    // 9. Обрезка строки
    public static String truncateString(String str, int length) {
        if (length > str.length()) {
            throw new IllegalArgumentException("Длина больше длины строки");
        }
        return str.substring(0, length);
    }

    // 10. Поиск элемента в массиве
    public static int findElement(int[] array, int element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                return i;
            }
        }
        throw new IllegalArgumentException("Элемент не найден");
    }

    // 11. Конвертация в двоичную систему
    public static String toBinaryString(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Недопустимое число для конвертации в двоичную систему");
        }
        return Integer.toBinaryString(number);
    }

    // 12. Проверка делимости
    public static boolean isDivisible(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return a % b == 0;
    }

    // 13. Чтение элемента списка
    public static int getListElement(List<Integer> list, int index) {
        return list.get(index);
    }

    // 14. Парольная проверка
    public static void checkPassword(String password) throws WeakPasswordException {
        if (password.length() < 8) {
            throw new WeakPasswordException("Пароль слишком слабый");
        }
    }

    // 15. Проверка даты
    public static boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Неверный формат даты", date, e.getErrorIndex());
        }
    }

    // 16. Конкатенация строк
    public static String concatStrings(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new NullPointerException("Одна из строк равна null");
        }
        return str1 + str2;
    }

    // 17. Остаток от деления
    public static int modulus(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return a % b;
    }

    // 18. Квадратный корень
    public static double sqrt(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Недопустимое число для извлечения корня");
        }
        return Math.sqrt(number);
    }

    // 19. Конвертер температуры
    public static double celsiusToFahrenheit(double celsius) {
        if (celsius < -273.15) {
            throw new IllegalArgumentException("Температура ниже абсолютного нуля");
        }
        return (celsius * 9 / 5) + 32;
    }

    // 20. Проверка строки на пустоту
    public static void checkStringForEmptyOrNull(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Строка пустая или равна null");
        }
    }
}
