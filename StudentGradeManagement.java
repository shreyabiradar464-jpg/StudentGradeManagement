
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int[] marks;
    int total;
    double percentage;
    String grade;

    Student(int rollNo, String name, int[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;

        calculateResult();
    }

    void calculateResult() {
        total = 0;

        for (int mark : marks) {
            total += mark;
        }

        percentage = total / 5.0;

        if (percentage >= 90) {
            grade = "A";
        } else if (percentage >= 75) {
            grade = "B";
        } else if (percentage >= 60) {
            grade = "C";
        } else if (percentage >= 35) {
            grade = "D";
        } else {
            grade = "Fail";
        }
    }

    void displayStudent() {
        System.out.println("\n------------------------------");
        System.out.println("Roll Number : " + rollNo);
        System.out.println("Name        : " + name);

        for (int i = 0; i < marks.length; i++) {
            System.out.println("Subject " + (i + 1) + "   : " + marks[i]);
        }

        System.out.println("Total Marks : " + total + "/500");
        System.out.printf("Percentage  : %.2f%%%n", percentage);
        System.out.println("Grade       : " + grade);
        System.out.println("------------------------------");
    }
}

public class StudentGradeManagement {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static int readInt(String message) {
        while (true) {
            System.out.print(message);

            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } else {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }
    }

    static void addStudent() {
        System.out.println("\n===== ADD STUDENT =====");

        int rollNo = readInt("Enter roll number: ");

        for (Student s : students) {
            if (s.rollNo == rollNo) {
                System.out.println("Roll number already exists!");
                return;
            }
        }

        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        int[] marks = new int[5];

        for (int i = 0; i < 5; i++) {
            while (true) {
                int mark = readInt(
                    "Enter marks for subject " + (i + 1)
                    + " (0-100): "
                );

                if (mark >= 0 && mark <= 100) {
                    marks[i] = mark;
                    break;
                } else {
                    System.out.println(
                        "Marks must be between 0 and 100."
                    );
                }
            }
        }

        Student student = new Student(rollNo, name, marks);
        students.add(student);

        System.out.println("Student added successfully!");
        student.displayStudent();
    }

    static void viewAllStudents() {
        System.out.println("\n===== ALL STUDENTS =====");

        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            s.displayStudent();
        }
    }

    static void searchStudent() {
        System.out.println("\n===== SEARCH STUDENT =====");

        int rollNo = readInt("Enter roll number to search: ");

        for (Student s : students) {
            if (s.rollNo == rollNo) {
                System.out.println("Student found!");
                s.displayStudent();
                return;
            }
        }

        System.out.println("Student not found.");
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n================================");
            System.out.println(" STUDENT GRADE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");

            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    viewAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    System.out.println(
                        "Thank you for using the system!"
                    );
                    sc.close();
                    return;

                default:
                    System.out.println(
                        "Invalid choice. Please select 1-4."
                    );
            }
        }
    }
}
