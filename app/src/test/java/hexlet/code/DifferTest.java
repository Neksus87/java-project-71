package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    @Test
    public void testDifferWithYaml() throws Exception {
        String yaml1 = "src/test/resources/file1.yml";
        String yaml2 = "src/test/resources/file2.yml";

        Map<String, Object> data1 = Differ.getData(yaml1);
        Map<String, Object> data2 = Differ.getData(yaml2);

        String expectedDiff = """
                - follow: false
                - proxy: 123.234.53.22
                - timeout: 50
                + timeout: 20
                + verbose: true
                """;

        String actualDiff = Differ.generate(data1, data2);

        assertEquals(expectedDiff.trim(), actualDiff.trim());
    }
}
