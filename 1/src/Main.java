import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число n: ");
        int n = scanner.nextInt();
        scanner.close();

        int primeWithMaxZeros = -1;
        int maxZeroCount = -1;

        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                String binary = Integer.toBinaryString(i);
                int zeroCount = countZeros(binary);

                if (zeroCount > maxZeroCount) {
                    maxZeroCount = zeroCount;
                    primeWithMaxZeros = i;
                }
            }
        }

        if (primeWithMaxZeros != -1) {
            System.out.println("Серед простих чисел до " + n + " — найбільше нулів має число: " + primeWithMaxZeros);
            System.out.println("Двійковий запис числа: " + primeWithMaxZeros + ": " + Integer.toBinaryString(primeWithMaxZeros));
            System.out.println("Кількість нулів: " + maxZeroCount);
        } else {
            System.out.println("Простих чисел до " + n + " не знайдено.");
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    private static int countZeros(String binary) {
        int count = 0;
        for (char c : binary.toCharArray()) {
            if (c == '0') count++;
        }
        return count;
    }
}
