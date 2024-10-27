package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided. Use -h for help.");
            return;
        }

        String format = "stylish"; // Значение по умолчанию для формата
        String filepath1 = null;
        String filepath2 = null;

        // Обработка аргументов командной строки
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                case "--help":
                    printHelp();
                    return;

                case "-f":
                case "--format":
                    if (i + 1 < args.length) {
                        format = args[i + 1];
                        i++; // Пропускаем следующий аргумент
                    } else {
                        System.err.println("Error: No format specified after -f or --format.");
                        return;
                    }
                    break;

                default:
                    if (args[i].startsWith("-")) {
                        System.err.println("Error: Unknown option " + args[i]);
                        return;
                    }
                    // Сохраняем пути файлов
                    if (filepath1 == null) {
                        filepath1 = args[i];
                    } else if (filepath2 == null) {
                        filepath2 = args[i];
                    }
                    break;
            }
        }

        // Проверка наличия двух файлов для сравнения
        if (filepath1 == null || filepath2 == null) {
            System.err.println("Error: You must provide two file paths.");
            return;
        }

        try {
            System.out.println("Comparing files: " + filepath1 + " and " + filepath2);
            System.out.println("Output format: " + format);

            Map<String, Object> data1 = getData(filepath1);
            Map<String, Object> data2 = getData(filepath2);

            // Выводим данные из файлов (можно добавить логику сравнения)
            System.out.println("Data from file 1: " + data1);
            System.out.println("Data from file 2: " + data2);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Метод для получения данных из файла
    private static Map<String, Object> getData(String filepath) throws IOException {
        String content = readFile(filepath);
        return parseJson(content);
    }

    // Метод для чтения файла
    private static String readFile(String filepath) throws IOException {
        Path path = Path.of(filepath);
        return Files.readString(path);
    }

    // Метод для парсинга JSON
    private static Map<String, Object> parseJson(String content) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() {}); // Обновлено
    }

    // Метод для вывода помощи
    private static void printHelp() {
        System.out.println("Usage: gendiff [-hV] [-f=format] filepath1 filepath2");
        System.out.println("Compares two configuration files and shows a difference.");
        System.out.println("      filepath1         path to first file");
        System.out.println("      filepath2         path to second file");
        System.out.println("  -f, --format=format   output format [default: stylish]");
        System.out.println("  -h, --help            Show this help message and exit.");
        System.out.println("  -V, --version         Print version information and exit.");
    }
}