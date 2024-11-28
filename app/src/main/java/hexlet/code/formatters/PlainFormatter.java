package hexlet.code.formatters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlainFormatter {
    public static String formatPlain(List<Map<String, Object>> data) {
        StringBuilder result = new StringBuilder();
        data.forEach((value) -> {
            try {
                result.append(getLine(value));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return result.toString();
    }

    public static String getLine(Map<String, Object> data) throws IOException {
        return switch (data.get("status").toString()) {
            case "added" -> "Property '"
                    + data.get("key")
                    + "' was added with value: "
                    + complexValueToString(data.get("value"))
                    + "\n";
            case "deleted" -> "Property '" + data.get("key") + "' was removed" + "\n";
            case "unchanged" -> "";
            case "changed" -> "Property '"
                    + data.get("key")
                    + "' was updated. From "
                    + complexValueToString(data.get("oldValue"))
                    + " to "
                    + complexValueToString(data.get("newValue"))
                    + "\n";

            default -> throw new IOException(" ");
        };
    }

    public static String complexValueToString(Object value) {
        if (value instanceof Arrays || value instanceof List<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        }
        if (value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value == null) {
            return "null";
        }
        return value.toString();
    }
}
