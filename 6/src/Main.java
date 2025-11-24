import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translate translator = new Translate();

        translator.addWord("hello", "привіт");
        translator.addWord("cat", "кіт");
        translator.addWord("dog", "собака");

        while (true) {
            System.out.println("\nМЕНЮ");
            System.out.println("1. Додати слово");
            System.out.println("2. Перекласти фразу");
            System.out.println("3. Вийти");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Введіть слово англійською: ");
                    String eng = scanner.nextLine();

                    System.out.print("Введіть переклад українською: ");
                    String ukr = scanner.nextLine();

                    translator.addWord(eng, ukr);
                    System.out.println("Слово додано.");
                    break;

                case "2":
                    System.out.print("Введіть фразу англійською: ");
                    String phrase = scanner.nextLine();

                    String result = translator.translatePhrase(phrase);
                    System.out.println("Переклад: " + result);
                    break;

                case "3":
                    System.out.println("Вихід...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Невірний вибір! Спробуйте ще раз.");
            }
        }
    }
}