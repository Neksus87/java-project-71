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
        return 0; // Возвращаем 0, если выполнение прошло успешно
    }

    public static void main(String... args) {
        if (args.length < 2) {
            System.err.println("Missing required parameters: '<filepath1>', '<filepath2>'");
            System.err.println("Usage: gendiff <filepath1> <filepath2> [--format=<format>]");
            System.exit(1);
        }

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
