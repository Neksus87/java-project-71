package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filePath, String fileFormat) throws IOException {
        switch (fileFormat) {
            case "json":
                return getDataJson(filePath);
            case "yml", "yaml":
                return getDataYaml(filePath);
            default:
                throw new IOException("Unknown file extension! -> " + fileFormat);
        }
    }

    public static Path getAbsolutePath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    private static Map<String, Object> getDataJson(String filePath) throws IOException {
        String fileContent = parseFile(filePath);
        ObjectMapper om = new ObjectMapper();
        return om.readValue(fileContent, new TypeReference<>() {});
    }

    private static Map<String, Object> getDataYaml(String filePath) throws IOException {
        String fileContent = parseFile(filePath);
        ObjectMapper om = new YAMLMapper();
        return om.readValue(fileContent, new TypeReference<>() {});
    }

    private static String parseFile(String filePath) throws IOException {
        return Files.readString(getAbsolutePath(filePath));
    }

    public static Map<String, Object> parce(String filePath1, String file1Format) {
        return Map.of();
    }
}
