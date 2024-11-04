package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.Map;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(paramLabel = "<file1> <file2>", description = "The two configuration files to compare.")
    private String[] files;

    public static void main(String[] args) {
        CommandLine.run(new App(), args);
    }

    @Override
    public void run() {
        try {
            if (files == null || files.length != 2) {
                System.err.println("Please provide exactly two files to compare.");
                return;
            }

            Map<String, Object> data1 = Differ.getData(files[0]);
            Map<String, Object> data2 = Differ.getData(files[1]);
            String diff = Differ.generate(data1, data2);
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
