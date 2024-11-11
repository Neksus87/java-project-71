package hexlet.code;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testDiffer() throws Exception {
        String json1 = "src/test/resources/file1.json";
        String json2 = "src/test/resources/file2.json";

        Map<String, Object> data1 = Differ.getData(json1);
        Map<String, Object> data2 = Differ.getData(json2);

        String expectedDiff = """
                - follow: false
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        String actualDiff = Differ.generate(data1, data2);

        assertEquals(expectedDiff.trim(), actualDiff.trim()); // Сравниваем без лишних пробелов
    }
}
