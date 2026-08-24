public class ATMService {


    public void deposit(Account account, double amount) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f. New balance: PHP %.2f%n", amount, account.getBalance());
    }

    public void deposit(Account account, double amount, String note) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f (%s). New balance: PHP %.2f%n", amount, note, account.getBalance());
    }


    public double depositAll(Account account, double... amounts) {
        double total = 0;
        for (double amt : amounts) {
            account.deposit(amt);
            total += amt;
        }
        return total;
    }


    public void tryToReplace(Account account) {
        account = new SavingAccount("XX-000", "Ghost Account", 0, 0);
        System.out.println("Inside tryToReplace: " + account);

    }

    public void addBonus(Account account, double bonus) {
        account.deposit(bonus);

    }


    public void transfer(Account from, Account to, double amount)
            throws InsufficientFundsException {
        from.withdraw(amount);
        to.deposit(amount);
    }
}
