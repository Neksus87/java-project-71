package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference; // Добавить этот импорт
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static Map<String, Object> getData(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("File does not exist or is not a valid file: " + filePath);
        }
        // Используем diamond operator <>
        return OBJECT_MAPPER.readValue(file, new TypeReference<>() {});
    }

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
                diffBuilder.append(String.format("  + %s: %s%n", key, value2));
            } else if (value2 == null) {
                // Ключ удалён
                diffBuilder.append(String.format("  - %s: %s%n", key, value1));
            } else if (!value1.equals(value2)) {
                // Ключ изменён
                diffBuilder.append(String.format("  - %s: %s%n", key, value1));
                diffBuilder.append(String.format("  + %s: %s%n", key, value2));
            } else {
                // Ключ не изменен
                diffBuilder.append(String.format("    %s: %s%n", key, value1));
            }
        }

        return diffBuilder.toString();
    }
}
