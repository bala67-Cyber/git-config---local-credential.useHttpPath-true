import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] id = new String[10];
        String[] name = new String[10];
        int[] age = new int[10];
        String[] course = new String[10];
        double[] grade = new double[10];
        boolean[] enrolled = new boolean[10];

        int count = 0, choice;

        do {
            System.out.println("\n1.Add  2.View  3.Search  4.Stats  5.Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("ID: ");
                    id[count] = sc.nextLine();

                    System.out.print("Name: ");
                    name[count] = sc.nextLine();

                    System.out.print("Age: ");
                    age[count] = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Course: ");
                    course[count] = sc.nextLine();

                    System.out.print("Grade: ");
                    grade[count] = sc.nextDouble();

                    System.out.print(" Enrolled(true/false): ");
                    enrolled[count] = sc.nextBoolean();

                    count++;
                    break;

                case 2:
                    for (int i = 0; i < count; i++) {
                        System.out.println(id[i] + " | " + name[i] + " | " + grade[i]);
                    }
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    String search = sc.next();
                    for (int i = 0; i < count; i++)
                        if (id[i].equals(search))
                            System.out.println(name[i] + " " + grade[i]);
                    break;

                case 4:
                    double total = 0;
                    for (int i = 0; i < count; i++)
                        total += grade[i];
                    System.out.println("Students: " + count);
                    System.out.println("Average: " + (count == 0 ? 0 : total / count));
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}
