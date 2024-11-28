package hexlet.code;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparison {
    public static List<Map<String, Object>> genDiff(Map<String, Object> data1, Map<String, Object> data2) {

        List<Map<String, Object>> result = new ArrayList<>();
        Set<Object> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (Object key: keys) {
            Map<String, Object> diff = new LinkedHashMap<>();

            if (!data1.containsKey(key)) {
                diff.put("status", "added");
                diff.put("key", key);
                diff.put("value", data2.get(key));
            } else if (!data2.containsKey(key)) {
                diff.put("status", "deleted");
                diff.put("key", key);
                diff.put("value", data1.get(key));
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                diff.put("status", "unchanged");
                diff.put("key", key);
                diff.put("value", data1.get(key));
            } else {
                diff.put("status", "changed");
                diff.put("key", key);
                diff.put("newValue", data2.get(key));
                diff.put("oldValue", data1.get(key));
            }
            result.add(diff);
        }
        return result;
    }
}
