import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Account account = new Account("Juan Dela Cruz", 1000.0);

        boolean running = true;

        while (running) {

            System.out.println();
            System.out.println("===== WELCOME TO CLI ATM =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Account Holder: " + account.getOwner());
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double d = input.nextDouble();
                    account.deposit(d);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double w = input.nextDouble();
                    account.withdraw(w);
                    break;

                case 4:
                    running = false;
                    System.out.println("Thank you for using CLI ATM!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        input.close();
    }
}    }
}

public class ATM {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    
        BankAccount account = new BankAccount(1000.00);

        int choice;

        do {
            System.out.println("\n===== ATM MACHINE =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.printf("Current Balance: %.2f%n",
                            account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
