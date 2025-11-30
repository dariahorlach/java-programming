import java.util.List;

public class Bank {
    private final List<Account> accounts;

    public Bank(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void transfer(Account from, Account to, int amount) {
        if (from == to || amount <= 0) return;

        Account firstLock = from.getId() < to.getId() ? from : to;
        Account secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.withdraw(amount)) {
                    to.deposit(amount);
                }
            }
        }
    }

    public int totalBalance() {
        return accounts.stream().mapToInt(Account::getBalance).sum();
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}