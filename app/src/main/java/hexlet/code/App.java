package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file.", paramLabel = "filepath1")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file.", paramLabel = "filepath2")
    private String filepath2;
    @Option(names = {"--format", "-f"}, defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]", paramLabel = "format")
    private String format;
    @Override
    public Integer call() throws Exception {

        var diff = Differ.generate(filepath1, filepath2, format);
        System.out.println(diff);

        return null;
    }

    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
