package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // Метод для чтения данных из файла
    public static Map<String, Object> getData(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("File does not exist or is not a valid file: " + filePath);
        }
        return OBJECT_MAPPER.readValue(file, new TypeReference<>() { });
    }

    // Метод для генерации различий
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        StringBuilder diffBuilder = new StringBuilder();
        TreeMap<String, Object> sortedKeys = new TreeMap<>();
        sortedKeys.putAll(data1);
        sortedKeys.putAll(data2);

        for (String key : sortedKeys.keySet()) {
            Object value1 = data1.get(key);
            Object value2 = data2.get(key);

            if (value1 == null) {
                // Ключ добавлен
                diffBuilder.append(String.format("+ %s: %s%n", key, value2));
            } else if (value2 == null) {
                // Ключ удалён
                diffBuilder.append(String.format("- %s: %s%n", key, value1));
            } else if (!value1.equals(value2)) {
                // Ключ изменён
                diffBuilder.append(String.format("- %s: %s%n", key, value1));
                diffBuilder.append(String.format("+ %s: %s%n", key, value2));
            } else {
                // Ключ не изменен
                diffBuilder.append(String.format("  %s: %s%n", key, value1));
            }
        }

        return diffBuilder.toString();
    }

    // Метод для сравнения файлов
    private static void compareFiles(String file1, String file2) {
        try {
            Map<String, Object> data1 = getData(file1);
            Map<String, Object> data2 = getData(file2);
            String diff = generate(data1, data2);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Please provide exactly two files to compare.");
            System.out.println("Usage: gendiff <file1> <file2>");
            return;
        }

        String file1 = args[0];
        String file2 = args[1];

        // Код для сравнения файлов
        compareFiles(file1, file2);
    }
}
