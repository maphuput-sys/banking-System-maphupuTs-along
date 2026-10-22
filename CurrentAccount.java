package banking;

public class CurrentAccount extends BankAccount implements Loanable {

    private double overdraft = 2000;

    public CurrentAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be greater than zero.");
        }

        setBalance(getBalance() + amount);
        System.out.println("Deposit successful.");
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be greater than zero.");
        }

        if (amount > getBalance() + overdraft) {
            throw new IllegalArgumentException("Overdraft limit exceeded.");
        }

        setBalance(getBalance() - amount);
        System.out.println("Withdrawal successful.");
    }

    @Override
    public double calculateInterest() {
        return 0;
    }

    @Override
    public void requestLoan(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan must be greater than zero.");
        }

        double limit = 10000;

        if (amount > limit) {
            throw new IllegalArgumentException("Maximum current account loan is M10000.");
        }

        setBalance(getBalance() + amount);
        System.out.println("Loan approved.");
    }

    @Override
    public void displayDetails() {
        System.out.println("\n===== CURRENT ACCOUNT =====");
        super.displayDetails();
    }
}