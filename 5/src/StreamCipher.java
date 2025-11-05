import java.io.*;

public class StreamCipher {
    public static void main(String[] args) {
        String inputFile = "text.txt";
        String encryptedFile = "encrypted.txt";
        String decryptedFile = "decrypted.txt";

        char keyChar = 'A';

        try {
            try (Writer writer = new EncryptWriter(new FileWriter(decryptedFile), keyChar);
                 BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
            }

            try (Reader reader = new DecryptReader(new FileReader(decryptedFile), keyChar);
                 BufferedWriter writer = new BufferedWriter(new FileWriter(encryptedFile))) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.write(c);
                }
            }

            System.out.println("Зашифрований файл: " + encryptedFile);
            System.out.println("Розшифрований файл: " + decryptedFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}