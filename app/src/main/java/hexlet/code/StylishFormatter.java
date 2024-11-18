package hexlet.code;

import java.util.Map;

public class StylishFormatter {

    public static String format(Map<String, Object> differences) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Object> entry : differences.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map<?, ?>) { // Используем параметризованный тип
                // Приводим к Map<String, Object>
                @SuppressWarnings("unchecked")
                Map<String, Object> nestedMap = (Map<String, Object>) value;
                result.append(key).append(": ").append(format(nestedMap)).append("\n");
            } else {
                result.append(key).append(": ").append(value.toString()).append("\n");
            }
        }
        return result.toString();
    }
}
