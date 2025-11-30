import java.lang.reflect.Field;
import java.util.Scanner;

public class ReflectionStringChange {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            String literalString = "Hello World";
            System.out.println("Рядок-літерал до зміни: " + literalString);

            System.out.print("Введіть рядок з клавіатури: ");
            String inputString = scanner.nextLine();
            System.out.println("Введений рядок до зміни: " + inputString);

            System.out.print("Введіть нове значення для заміни: ");
            String newValue = scanner.nextLine();

            modifyStringValue(literalString, newValue);
            modifyStringValue(inputString, newValue);

            System.out.println("\nПісля зміни:");
            System.out.println("Рядок-літерал: " + literalString);
            System.out.println("Введений рядок: " + inputString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void modifyStringValue(String target, String newValue) throws Exception {
        Class<String> stringClass = String.class;

        Field valueField = stringClass.getDeclaredField("value");
        valueField.setAccessible(true);

        Object internalValue = valueField.get(target);

        if (internalValue instanceof char[]) {
            char[] value = (char[]) internalValue;
            char[] newChars = newValue.toCharArray();

            System.arraycopy(newChars, 0, value, 0, Math.min(value.length, newChars.length));

            for (int i = newChars.length; i < value.length; i++) {
                value[i] = ' ';
            }

        } else if (internalValue instanceof byte[]) {
            byte[] value = (byte[]) internalValue;
            byte[] newBytes = newValue.getBytes();

            System.arraycopy(newBytes, 0, value, 0, Math.min(value.length, newBytes.length));
            for (int i = newBytes.length; i < value.length; i++) {
                value[i] = ' ';
            }
        } else {
            throw new IllegalStateException("Невідомий тип поля value: " + internalValue.getClass());
        }
    }
}
