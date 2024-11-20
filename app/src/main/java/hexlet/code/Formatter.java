package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.Plain.formatPlain;
import static hexlet.code.formatters.Stylish.formatStylish;

public class Formatter {
    public static String constructFormat(Map<String, Object> map1, Map<String, Object> map2,
                                         Map<String, List<Object>> diffMap, String format) throws IOException {
        // Преобразовать diffMap в строковый формат
        Map<String, String> diffMapAsString = new HashMap<>();

        for (Map.Entry<String, List<Object>> entry : diffMap.entrySet()) {
            String valueAsString = entry.getValue().toString();  // Может потребоваться более сложное преобразование
            diffMapAsString.put(entry.getKey(), valueAsString);
        }

        return switch (format) {
            case "stylish" -> formatStylish(map1, map2, diffMapAsString);
            case "plain" -> formatPlain(map1, map2, diffMapAsString);
            default -> throw new IOException("Unknown format! => " + format);
        };
    }
}
