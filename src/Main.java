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

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.println("\n--- REGISTER STUDENT ---");

                    System.out.print("Student ID: ");
                    String id = sc.nextLine();

                    System.out.print("Full Name: ");
                    String name = sc.nextLine();

                    System.out.print("Program: ");
                    String program = sc.nextLine();

                    boolean valid = false;

                    for (String p : validPrograms) {
                        if (p.equalsIgnoreCase(program)) {
                            program = p;
                            valid = true;
                            break;
                        }
                    }

                    if (!valid) {
                        System.out.println("[ERROR] Invalid program.");
                        break;
                    }

                    System.out.print("Year Level: ");
                    int year = Integer.parseInt(sc.nextLine());

                    if (year < 1 || year > 4) {
                        System.out.println("[ERROR] Invalid year level.");
                        break;
                    }

                    students.add(new Student(id, name, program, year));

                    System.out.println("[OK] Student registered successfully!");

                    break;

                case 2:

                    System.out.println("\n--- ADD COURSE ---");

                    System.out.print("Course Code: ");
                    String code = sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Units: ");
                    int units = Integer.parseInt(sc.nextLine());

                    System.out.print("Capacity: ");
                    int capacity = Integer.parseInt(sc.nextLine());

                    courses.add(new Course(code, title, units, capacity));

                    System.out.println("[OK] Course added.");

                    break;

                case 3:

                    System.out.println("\n--- ENROLL STUDENT ---");

                    System.out.print("Student ID: ");
                    String sid = sc.nextLine();

                    Student student = findStudent(students, sid);

                    if (student == null) {
                        System.out.println("[ERROR] Student not found.");
                        break;
                    }

                    System.out.print("Course Code: ");
                    String ccode = sc.nextLine();

                    Course course = findCourse(courses, ccode);

                    if (course == null) {
                        System.out.println("[ERROR] Course not found.");
                        break;
                    }

                    if (course.isFull()) {
                        System.out.println("[ERROR] Course is full.");
                        break;
                    }

                    ArrayList<String> list = enrollments.get(sid);

                    if (list == null) {
                        list = new ArrayList<>();
                        enrollments.put(sid, list);
                    }

                    if (list.contains(ccode)) {
                        System.out.println("[ERROR] Student is already enrolled.");
                        break;
                    }

                    list.add(ccode);
                    course.addOneEnrollee();

                    System.out.println("[OK] " + student.getFullName()
                            + " enrolled in " + course.getCourseCode()
                            + " (" + course.getTitle() + ")");

                    break;

                case 4:

                    System.out.println("\n--- STUDENTS ---");

                    if (students.isEmpty()) {
                        System.out.println("No students yet.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s.describe());
                        }
                    }

                    break;

                case 5:

                    System.out.println("\n--- COURSES ---");

                    if (courses.isEmpty()) {
                        System.out.println("No courses yet.");
                    } else {
                        for (Course c : courses) {
                            System.out.println(
                                    c.getCourseCode()
                                            + " | "
                                            + c.getTitle()
                                            + " | "
                                            + c.getUnits()
                                            + " units | "
                                            + c.getEnrolledCount()
                                            + "/"
                                            + c.getCapacity()
                            );
                        }
                    }

                    break;

                case 6:

                    System.out.println("\n--- STUDENT LOAD ---");

                    System.out.print("Student ID: ");
                    String studentID = sc.nextLine();

                    Student stu = findStudent(students, studentID);

                    if (stu == null) {
                        System.out.println("[ERROR] Student not found.");
                        break;
                    }

                    System.out.println("Student: " + stu.getFullName());

                    ArrayList<String> enrolled = enrollments.get(studentID);

                    if (enrolled == null || enrolled.isEmpty()) {
                        System.out.println("No enrolled courses.");
                        break;
                    }

                    int total = 0;

                    for (String courseCode : enrolled) {

                        Course c = findCourse(courses, courseCode);

                        if (c != null) {
                            System.out.println(
                                    c.getCourseCode()
                                            + " "
                                            + c.getTitle()
                                            + " "
                                            + c.getUnits()
                                            + " units");

                            total += c.getUnits();
                        }
                    }

                    System.out.println("-------------------------");
                    System.out.println("Total Units: " + total);

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

        System.out.println();
        System.out.println("========================================");
        System.out.println("      LICEO ENROLLMENT SYSTEM (CLI)");
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

    static Course findCourse(ArrayList<Course> list, String code) {

        for (Course c : list) {
            if (c.getCourseCode().equals(code)) {
                return c;
            }
        }

        return null;
    }
}
