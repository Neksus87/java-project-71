package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.util.Map;

public class Parser {
    private static Map<String, Object> parseYaml(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    private static Map<String, Object> parseJson(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, new TypeReference<>() { });
    }

    public static Map<String, Object> parse(String content, String dataFormat) throws Exception {
        return switch (dataFormat) {
            case "yml", "yaml" -> parseYaml(content);
            case "json" -> parseJson(content);
            default -> throw new Exception("Unknown format: '" + dataFormat + "'");
        };
    }
}
