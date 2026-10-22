package banking;

public class SavingAccount extends BankAccount implements Loanable {

    public SavingAccount(String accountNumber, String accountHolder, double balance) {
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

        if (getBalance() - amount < 100) {
            throw new IllegalArgumentException(
                    "Saving account must keep a minimum balance of M100."
            );
        }

        setBalance(getBalance() - amount);
        System.out.println("Withdrawal successful.");
    }

    @Override
    public double calculateInterest() {
        return getBalance() * 0.05;
    }

    @Override
    public void requestLoan(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Loan must be greater than zero.");
        }

        double limit = 5000;

        if (amount > limit) {
            throw new IllegalArgumentException("Maximum savings loan is M5000.");
        }

        setBalance(getBalance() + amount);
        System.out.println("Loan approved.");
    }

    @Override
    public void displayDetails() {
        System.out.println("\n===== SAVING ACCOUNT =====");
        super.displayDetails();
    }
}
