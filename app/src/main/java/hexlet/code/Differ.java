package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Differ {
    public static String generate(String filepath1, String filepath2, String outputFormat) throws Exception {

        String fileData1 = getData(filepath1);
        String fileData2 = getData(filepath2);

        var fileFormat1 = getFileFormat(filepath1);
        var fileFormat2 = getFileFormat(filepath2);

        Map<String, Object> keysAndValuesFile1 = Parser.parse(fileData1, fileFormat1);
        Map<String, Object> keysAndValuesFile2 = Parser.parse(fileData2, fileFormat2);

        var result = Comparison.genDiff(keysAndValuesFile1, keysAndValuesFile2);

        return Formatter.setResultFormat(result, outputFormat).trim();
    }

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String getData(String pathString) throws Exception {
        Path path = Path.of(pathString);
        path.toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Files.readString(path);
    }

    public static String getFileFormat(String filePath) {
        String[] result = filePath.split("\\.");
        return result[result.length - 1];
    }
}
