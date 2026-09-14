import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        PaymentGateway gateway = new PaymentGateway();

        gateway.add(new GCashPayment(1001, "Ana", 1500.00, "0917-555-0134"));
        gateway.add(new MayaPayment(1002, "Jerome", 899.50, "jerome@liceo.edu.ph"));
        gateway.add(new CashPayment(1003, "Liza", 250.00));

        int choice;

        do {
            System.out.println();
            System.out.println("=== CAMPUS CANTEEN PAYMENT GATEWAY ===");
            System.out.println("[1] Make Payment");
            System.out.println("[2] Show All Receipts");
            System.out.println("[3] Find Payment");
            System.out.println("[4] Total Collected");
            System.out.println("[5] Refund All Refundable Payments");
            System.out.println("[6] Show Service Fees");
            System.out.println("[0] Exit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println();
                    System.out.println("Payment added successfully.");
                    System.out.println("Use the existing sample payments for testing.");
                    break;

                case 2:
                    System.out.println();
                    System.out.println("All Payment Receipts:");
                    gateway.processAll();
                    break;

                case 3:
                    System.out.print("Enter payment ID: ");
                    int id = sc.nextInt();

                    Payment payment = gateway.findById(id);

                    if (payment != null) {
                        payment.printReceipt();
                    } else {
                        System.out.println("Payment not found.");
                    }
                    break;

                case 4:
                    System.out.printf("Total Collected: PHP %.2f%n",
                            gateway.totalCollected());
                    break;

                case 5:
                    System.out.println();
                    System.out.println("Refunding every payment that can be refunded:");
                    gateway.refundAll();
                    break;

                case 6:
                    System.out.println();
                    System.out.println("Service fees:");
                    gateway.showServiceFees();
                    break;

                case 0:
                    System.out.println("Thank you for using the Payment Gateway.");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

        sc.close();
    }
}
