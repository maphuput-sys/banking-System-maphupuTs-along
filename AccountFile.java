package banking;

import java.io.*;
import java.util.ArrayList;

public final class AccountFile {

    private static final String FILE_NAME = "accounts.txt";

    private AccountFile() {
    }

    // Overwrite accounts.txt with the full current list
    public static void saveAllAccounts(ArrayList<BankAccount> accounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (BankAccount account : accounts) {

                String type = (account instanceof SavingAccount) ? "SAVINGS" : "CURRENT";

                writer.println(
                        type + "," +
                        account.getAccountNumber() + "," +
                        account.getAccountHolder() + "," +
                        account.getBalance()
                );
            }
        } catch (IOException e) {
            System.out.println("Could not save accounts: " + e.getMessage());
        }
    }

    // Read accounts.txt; rebuild the list; return highest account number seen
    public static int loadAllAccounts(ArrayList<BankAccount> accounts) {

        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return 1000;
        }

        int highest = 1000;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");
                if (parts.length < 4) {
                    continue;
                }

                String type    = parts[0].trim();
                String accNum  = parts[1].trim();
                String holder  = parts[2].trim();
                double balance = Double.parseDouble(parts[3].trim());

                BankAccount account;

                if (type.equalsIgnoreCase("SAVINGS")) {
                    account = new SavingAccount(accNum, holder, balance);
                } else {
                    account = new CurrentAccount(accNum, holder, balance);
                }

                accounts.add(account);

                try {
                    int n = Integer.parseInt(accNum.replace("ACC", ""));
                    if (n > highest) {
                        highest = n;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load accounts: " + e.getMessage());
        }

        return highest;
    }
}