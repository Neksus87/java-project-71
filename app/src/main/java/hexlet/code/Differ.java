package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, Object> getData(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("File does not exist or is not a valid file: " + filePath);
        }
        return objectMapper.readValue(file, Map.class);
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
                // Key added
                diffBuilder.append(String.format("  + %s: %s%n", key, value2));
            } else if (value2 == null) {
                // Key removed
                diffBuilder.append(String.format("  - %s: %s%n", key, value1));
            } else if (!value1.equals(value2)) {
                // Key changed
                diffBuilder.append(String.format("  - %s: %s%n", key, value1));
                diffBuilder.append(String.format("  + %s: %s%n", key, value2));
            } else {
                // Key unchanged
                diffBuilder.append(String.format("    %s: %s%n", key, value1));
            }
        }

        return diffBuilder.toString();
    }
}
