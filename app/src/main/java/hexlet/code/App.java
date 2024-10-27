package hexlet.code;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments provided. Use -h for help.");
            return;
        }

        String format = "stylish"; // Значение по умолчанию для формата

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                case "--help":
                    printHelp();
                    return;

                case "-f":
                case "--format":
                    if (i + 1 < args.length) {
                        format = args[i + 1];
                        i++; // Пропускаем следующий аргумент
                    } else {
                        System.err.println("Error: No format specified after -f or --format.");
                        return;
                    }
                    break;

                default:
                    if (args[i].startsWith("-")) {
                        System.err.println("Error: Unknown option " + args[i]);
                        return;
                    }
                    break;
            }
        }

        // Здесь вы можете добавить логику для обработки файлов, используя переменные filepath1, filepath2 и format
        // Например:
        if (args.length < 2) {
            System.err.println("Error: You must provide two file paths.");
            return;
        }

        String filepath1 = args[args.length - 2];
        String filepath2 = args[args.length - 1];

        System.out.println("Comparing files: " + filepath1 + " and " + filepath2);
        System.out.println("Output format: " + format);

        // Здесь добавьте логику сравнения файлов
    }

    private static void printHelp() {
        System.out.println("Usage: gendiff [-hV] [-f=format] filepath1 filepath2");
        System.out.println("Compares two configuration files and shows a difference.");
        System.out.println("      filepath1         path to first file");
        System.out.println("      filepath2         path to second file");
        System.out.println("  -f, --format=format   output format [default: stylish]");
        System.out.println("  -h, --help            Show this help message and exit.");
        System.out.println("  -V, --version         Print version information and exit.");
    }
}