import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class EnrollmentApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();

        String[] validPrograms = {"BSIT", "BSCS"};

        int choice = -1;

        while (choice != 0) {

            printMenu();
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    // Register Student
                    System.out.println("--- REGISTER STUDENT ---");

                    System.out.print("Student ID : ");
                    String id = sc.nextLine();

                    System.out.print("Full Name : ");
                    String name = sc.nextLine();

                    System.out.print("Program : ");
                    String program = sc.nextLine().toUpperCase();

                    boolean validProgram = false;
                    for (String p : validPrograms) {
                        if (p.equals(program)) {
                            validProgram = true;
                            break;
                        }
                    }

                    if (!validProgram) {
                        System.out.println("[ERROR] Invalid program.");
                        break;
                    }

                    System.out.print("Year Level : ");
                    int yearLevel = Integer.parseInt(sc.nextLine());

                    if (yearLevel < 1 || yearLevel > 4) {
                        System.out.println("[ERROR] Invalid year level.");
                        break;
                    }

                    students.add(new Student(id, name, program, yearLevel));
                    System.out.println("[OK] Student registered successfully!");
                    break;

                case 2:
                    // Add Course
                    System.out.println("--- ADD COURSE ---");

                    System.out.print("Course Code : ");
                    String code = sc.nextLine();

                    System.out.print("Course Title : ");
                    String title = sc.nextLine();

                    System.out.print("Units : ");
                    int units = Integer.parseInt(sc.nextLine());

                    System.out.print("Capacity : ");
                    int capacity = Integer.parseInt(sc.nextLine());

                    courses.add(new Course(code, title, units, capacity));

                    System.out.println("[OK] Course added!");
                    break;

                case 3:
                    // Enroll Student
                    System.out.println("--- ENROLL STUDENT ---");

                    System.out.print("Student ID : ");
                    String sid = sc.nextLine();

                    Student student = findStudent(students, sid);

                    if (student == null) {
                        System.out.println("[ERROR] Student not found.");
                        break;
                    }

                    System.out.print("Course Code : ");
                    String ccode = sc.nextLine();

                    Course selectedCourse = null;

                    for (Course c : courses) {
                        if (c.getCourseCode().equals(ccode)) {
                            selectedCourse = c;
                            break;
                        }
                    }

                    if (selectedCourse == null) {
                        System.out.println("[ERROR] Course not found.");
                        break;
                    }

                    if (!enrollments.containsKey(sid)) {
                        enrollments.put(sid, new ArrayList<>());
                    }

                    ArrayList<String> list = enrollments.get(sid);

                    if (list.contains(ccode)) {
                        System.out.println("[ERROR] Student is already enrolled in " + ccode + ".");
                        break;
                    }

                    if (selectedCourse.getEnrolled() >= selectedCourse.getCapacity()) {
                        System.out.println("[ERROR] Course is full.");
                        break;
                    }

                    list.add(ccode);
                    selectedCourse.setEnrolled(selectedCourse.getEnrolled() + 1);

                    System.out.println("[OK] " + student.getName()
                            + " enrolled in " + selectedCourse.getCourseCode()
                            + " (" + selectedCourse.getTitle() + ").");
                    break;

                case 4:
                    // View Students
                    System.out.println("--- STUDENTS ---");

                    for (Student s : students) {
                        System.out.println(
                                s.getStudentId() + " | "
                                        + s.getName() + " | "
                                        + s.getProgram() + " | Year "
                                        + s.getYearLevel());
                    }
                    break;

                case 5:
                    // View Courses
                    System.out.println("--- COURSES ---");

                    for (Course c : courses) {
                        System.out.println(
                                c.getCourseCode() + " | "
                                        + c.getTitle() + " | "
                                        + c.getUnits() + " units | "
                                        + c.getEnrolled() + "/"
                                        + c.getCapacity());
                    }
                    break;

                case 6:
                    // Student Load
                    System.out.print("Student ID : ");
                    String studId = sc.nextLine();

                    Student s = findStudent(students, studId);

                    if (s == null) {
                        System.out.println("[ERROR] Student not found.");
                        break;
                    }

                    System.out.println("--- STUDENT LOAD: " + s.getName() + " ---");

                    int totalUnits = 0;

                    ArrayList<String> enrolled = enrollments.get(studId);

                    if (enrolled != null) {

                        for (String courseCode : enrolled) {

                            for (Course c : courses) {

                                if (c.getCourseCode().equals(courseCode)) {

                                    System.out.println(
                                            c.getCourseCode() + " "
                                                    + c.getTitle() + " "
                                                    + c.getUnits() + " units");

                                    totalUnits += c.getUnits();
                                }
                            }
                        }
                    }

                    System.out.println("--------------------------------");
                    System.out.println("Total Units: " + totalUnits);
                    break;

                case 0:
                    System.out.println("Thank you for using the Liceo Enrollment System!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }

    static void printMenu() {

        System.out.println("========================================");
        System.out.println("LICEO ENROLLMENT SYSTEM (CLI)");
        System.out.println("========================================");
        System.out.println("[1] Register Student");
        System.out.println("[2] Add Course Offering");
        System.out.println("[3] Enroll Student to Course");
        System.out.println("[4] View All Students");
        System.out.println("[5] View All Courses");
        System.out.println("[6] View Student Load (Courses + Total Units)");
        System.out.println("[0] Exit");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");
    }

    static Student findStudent(ArrayList<Student> list, String id) {

        for (Student s : list) {
            if (s.getStudentId().equals(id)) {
                return s;
            }
        }

        return null;
    }
}
