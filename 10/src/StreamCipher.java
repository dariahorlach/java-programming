import java.io.*;
import java.util.*;
import java.util.logging.*;

public class StreamCipher {
    private static final Logger logger = Logger.getLogger(StreamCipher.class.getName());
    private static ResourceBundle messages;

    static {
        try {
            LogManager.getLogManager().reset();

            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            logger.addHandler(consoleHandler);

            FileHandler fileHandler = new FileHandler("cipher_log.txt", true);
            fileHandler.setLevel(Level.FINE);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Не вдалося створити лог-файл: " + e.getMessage());
        }
    }

public static void main(String[] args) {
    Locale locale = chooseLanguage();
    messages = ResourceBundle.getBundle("resources.location.messages", locale);

    Scanner scanner = new Scanner(System.in);

    System.out.print(messages.getString("enter.input.file"));
    String inputFile = scanner.nextLine();

    System.out.print(messages.getString("enter.encrypted.file"));
    String encryptedFile = scanner.nextLine();

    System.out.print(messages.getString("enter.decrypted.file"));
    String decryptedFile = scanner.nextLine();

    System.out.print(messages.getString("enter.key"));
    char keyChar = scanner.nextLine().charAt(0);

    logger.info(messages.getString("start.program"));

    try {
        logger.fine(messages.getString("start.encrypt") + inputFile);
        try (Writer writer = new EncryptWriter(new FileWriter(decryptedFile), keyChar);
             BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
        }
        logger.info(messages.getString("success.encrypt") + encryptedFile);

        logger.fine(messages.getString("start.decrypt") + encryptedFile);
        try (Reader reader = new DecryptReader(new FileReader(decryptedFile), keyChar);
             BufferedWriter writer = new BufferedWriter(new FileWriter(encryptedFile))) {

            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }
        logger.info(messages.getString("success.decrypt") + decryptedFile);

    } catch (FileNotFoundException e) {
        logger.severe(messages.getString("error.file.not.found") + e.getMessage());
    } catch (IOException e) {
        logger.log(Level.SEVERE, messages.getString("error.io"), e);
    } catch (Exception e) {
        logger.log(Level.WARNING, messages.getString("error.unknown"), e);
    }

    logger.info(messages.getString("end.program"));
}

    private static Locale chooseLanguage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Вибір мови / Choose language ===");
        System.out.println("1 - Українська");
        System.out.println("2 - English");
        System.out.print(">> ");

        int choice = 1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (Exception ignored) {}

        if (choice == 2) {
            return new Locale("en", "US");
        } else {
            return new Locale("uk", "UA");
        }
    }
}
