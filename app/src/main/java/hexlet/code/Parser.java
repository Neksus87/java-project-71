package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.util.Map;

public class Parser {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parse(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new Exception("File does not exist or is not a valid file: " + filePath);
        }
        return OBJECT_MAPPER.readValue(file, new TypeReference<Map<String, Object>>() { });
    }
}
