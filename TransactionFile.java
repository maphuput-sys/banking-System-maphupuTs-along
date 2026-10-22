package banking;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public final class TransactionFile {

    private TransactionFile() {
    }

    public static void log(
            String accountNumber,
            String type,
            double amount,
            double balance,
            String message) {

        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter("transactions.txt", true)
            );

            writer.println(
                    LocalDateTime.now() +
                    " | Account: " + accountNumber +
                    " | Type: " + type +
                    " | Amount: M" + amount +
                    " | Balance: M" + balance +
                    " | " + message
            );

            writer.close();

        } catch (IOException e) {
            System.out.println("Could not write transaction.");
        }
    }
}