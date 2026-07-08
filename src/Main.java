import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] studentID = new int[10];
        String[] fullName = new String[10];
        int[] age = new int[10];
        String[] course = new String[10];
        double[] grade = new double[10];
        boolean[] enrolled = new boolean[10];

        int count = 0;
        int choice;

        do {

            System.out.println("\n===== STUDENT INFORMATION SYSTEM =====");
            System.out.println("[1] Add Student");
            System.out.println("[2] View All Students");
            System.out.println("[3] Search Student by ID");
            System.out.println("[4] View Statistics");
            System.out.println("[5] Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    if (count == studentID.length) {
                        System.out.println("\nStudent list is already full!");
                        break;
                    }

                    System.out.print("\nEnter Student ID: ");
                    studentID[count] = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Full Name: ");
                    fullName[count] = sc.nextLine();

                    do {
                        System.out.print("Enter Age: ");
                        age[count] = sc.nextInt();

                        if (age[count] <= 0) {
                            System.out.println("Invalid! Age must be greater than 0.");
                        }

                    } while (age[count] <= 0);

                    sc.nextLine();

                    System.out.print("Enter Course: ");
                    course[count] = sc.nextLine();

                    do {

                        System.out.print("Enter Grade: ");
                        grade[count] = sc.nextDouble();

                        if (grade[count] < 0 || grade[count] > 100) {
                            System.out.println("Invalid! Grade must be between 0 and 100.");
                        }

                    } while (grade[count] < 0 || grade[count] > 100);

                    System.out.print("Is Enrolled (true/false): ");
                    enrolled[count] = sc.nextBoolean();

                    count++;

                    System.out.println("\n>> Student added successfully!");

                    break;

                case 2:

                    if (count == 0) {
                        System.out.println("\nNo student records found.");
                        break;
                    }

                    System.out.println("\n----------- STUDENT RECORDS -----------");

                    System.out.printf("%-8s %-20s %-5s %-10s %-8s %-15s%n",
                            "ID", "NAME", "AGE", "COURSE", "GRADE", "STANDING");

                    for (int i = 0; i < count; i++) {

                        String standing;

                        if (grade[i] >= 90) {
                            standing = "Dean's Lister";
                        } else if (grade[i] >= 75) {
                            standing = "Passed";
                        } else {
                            standing = "Failed";
                        }

                        System.out.printf("%-8d %-20s %-5d %-10s %-8.1f %-15s%n",
                                studentID[i],
                                fullName[i],
                                age[i],
                                course[i],
                                grade[i],
                                standing);
                    }

                    break;

                case 3:

                    if (count == 0) {
                        System.out.println("\nNo student records found.");
                        break;
                    }

                    System.out.print("\nEnter Student ID to search: ");
                    int searchID = sc.nextInt();

                    boolean found = false;

                    for (int i = 0; i < count; i++) {

                        if (studentID[i] == searchID) {

                            System.out.println("\nStudent Found!");
                            System.out.println("------------------------");
                            System.out.println("Student ID : " + studentID[i]);
                            System.out.println("Name       : " + fullName[i]);
                            System.out.println("Age        : " + age[i]);
                            System.out.println("Course     : " + course[i]);
                            System.out.println("Grade      : " + grade[i]);
                            System.out.println("Enrolled   : " + enrolled[i]);

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Student not found.");
                    }

                    break;

                case 4:

                    if (count == 0) {
                        System.out.println("\nNo student records found.");
                        break;
                    }

                    double totalGrade = 0;
                    double highestGrade = grade[0];
                    String topStudent = fullName[0];

                    for (int i = 0; i < count; i++) {

                        totalGrade += grade[i];

                        if (grade[i] > highestGrade) {
                            highestGrade = grade[i];
                            topStudent = fullName[i];
                        }
                    }

                    double average = totalGrade / count;

                    int enrolledCount = 0;

                    for (boolean status : enrolled) {
                        if (status) {
                            enrolledCount++;
                        }
                    }

                    System.out.println("\n----------- STATISTICS -----------");
                    System.out.println("Total Students      : " + count);
                    System.out.println("Enrolled Students   : " + enrolledCount);
                    System.out.printf("Average Grade       : %.2f%n", average);
                    System.out.println("Top Student         : " + topStudent + " (" + highestGrade + ")");

                    break;

                case 5:
                    System.out.println("\nThank you for using the Student Information System. Goodbye!");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please enter 1-5.");
            }

        } while (choice != 5);

        sc.close();
    }
}
