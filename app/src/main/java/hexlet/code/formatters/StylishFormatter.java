package hexlet.code.formatters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String formatStylish(List<Map<String, Object>> data) {
        StringBuilder result = new StringBuilder("{");
        data.forEach((value) -> {
            try {
                result.append(getLine(value));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        result.append("\n}");

        return result.toString();
    }

    public static String getLine(Map<String, Object> data) throws IOException {
        return switch (data.get("status").toString()) {
            case "added" -> "\n  + " + data.get("key") + ": " + data.get("value");
            case "deleted" -> "\n  - " + data.get("key") + ": " + data.get("value");
            case "unchanged" -> "\n    " + data.get("key") + ": " + data.get("value");
            case "changed" -> "\n  - "
                    + data.get("key")
                    + ": "
                    + data.get("oldValue")
                    + "\n  + "
                    + data.get("key")
                    + ": "
                    + data.get("newValue");

            default -> throw new IOException(" ");
        };
    }
}
