package hexlet.code;

import lombok.Getter;
import lombok.Setter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparison {
    public static final String DELETED = "deleted";
    public static final String ADDED = "added";
    public static final String CHANGED = "changed";
    public static final String UNCHANGED = "unchanged";

    @Getter
    @Setter
    public static class Status {
        private String statusName;
        private Object oldValue;
        private Object newValue;

        public Status(String statusName, Object oldValue, Object newValue) {
            this.statusName = statusName;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }
    }

    public static Map<String, Object> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        for (String key : keys) {
            if (!data2.containsKey(key)) {
                result.put(key, new Status(DELETED, data1.get(key), null));
            } else if (!data1.containsKey(key)) {
                result.put(key, new Status(ADDED, null, data2.get(key)));
            } else if (!Objects.equals(data1.get(key), data2.get(key))) {
                result.put(key, new Status(CHANGED, data1.get(key), data2.get(key)));
            } else {
                result.put(key, new Status(UNCHANGED, data1.get(key), null));
            }
        }
        return result;
    }
}
