import java.util.*;
import java.util.concurrent.*;

public class BankTransferDemo {
    public static void main(String[] args) throws InterruptedException {
        int numAccounts = 100;
        int numThreads = 2000;
        Random rand = new Random();

        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < numAccounts; i++) {
            accounts.add(new Account(i, rand.nextInt(1000) + 500));
        }

        Bank bank = new Bank(accounts);

        int initialTotal = bank.totalBalance();
        System.out.println("Початкова сума в банку: " + initialTotal);

        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                Account from = accounts.get(rand.nextInt(numAccounts));
                Account to = accounts.get(rand.nextInt(numAccounts));
                int amount = rand.nextInt(200);
                bank.transfer(from, to, amount);
            });
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        int finalTotal = bank.totalBalance();
        System.out.println("Кінцева сума в банку: " + finalTotal);

        if (initialTotal == finalTotal) {
            System.out.println("Баланс збережено! Всі перекази коректні.");
        } else {
            System.out.println("Помилка: баланс змінився!");
        }
    }
}
