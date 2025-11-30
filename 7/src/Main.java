import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть число n: ");
        int n = scanner.nextInt();
        scanner.close();

        java.util.function.IntPredicate isPrime = num ->
                num > 1 && IntStream.rangeClosed(2, (int) Math.sqrt(num))
                        .allMatch(div -> num % div != 0);

        java.util.function.Function<Integer, Long> countZeros = num ->
                Integer.toBinaryString(num).chars()
                        .filter(ch -> ch == '0')
                        .count();

        Optional<Integer> result = IntStream.rangeClosed(2, n)
                .filter(isPrime)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        countZeros,
                        (a, b) -> a,
                        LinkedHashMap::new
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        if (result.isPresent()) {
            int prime = result.get();
            String binary = Integer.toBinaryString(prime);
            long zeros = countZeros.apply(prime);

            System.out.println("Серед простих чисел до " + n +
                    " найбільше нулів має число: " + prime);
            System.out.println("Двійковий запис: " + binary);
            System.out.println("Кількість нулів: " + zeros);
        } else {
            System.out.println("Простих чисел до " + n + " не знайдено.");
        }
    }
}
