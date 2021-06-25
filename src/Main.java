import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Scanner sc = new Scanner(System.in);
        String expression;
        while (true) {
            System.out.println("\nВведите выражение: ");
            expression = sc.nextLine();
            try {
                System.out.println(parser.solve(expression));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Произошла критическая ошибка. Подробнее: \n" + e.getMessage() + "\nДо свидания.");
                System.exit(1);
            }
        }
    }
}
