package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> getData(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("File does not exist or is not a valid file: " + filePath);
        }
        return OBJECT_MAPPER.readValue(file, new TypeReference<Map<String, Object>>() { });
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
                diffBuilder.append(String.format("+ %s: %s%n", key, value2));
            } else if (value2 == null) {
                diffBuilder.append(String.format("- %s: %s%n", key, value1));
            } else if (!value1.equals(value2)) {
                diffBuilder.append(String.format("- %s: %s%n", key, value1));
                diffBuilder.append(String.format("+ %s: %s%n", key, value2));
            } else {
                diffBuilder.append(String.format("  %s: %s%n", key, value1));
            }
        }

        return diffBuilder.toString();
    }
}
