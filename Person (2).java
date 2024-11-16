public abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public abstract String getDetails();
}

public interface ScholarshipEligibility {
    boolean isEligibleForScholarship();
}

public class Student extends Person implements ScholarshipEligibility {
    private static int totalStudents = 0;
    private int studentId;
    private int[] grades;

    public Student(String name, int age, int studentId, int[] grades) {
        super(name, age);
        this.studentId = studentId;
        this.grades = grades;
        totalStudents++;
    }

    public static int getTotalStudents() {
        return totalStudents;
    }

    public double calculateAverageGrade() {
        return Arrays.stream(grades).average().orElse(0);
    }

    @Override
    public boolean isEligibleForScholarship() {
        return calculateAverageGrade() > 85;
    }

    @Override
    public String getDetails() {
        return "Student ID: " + studentId + ", Name: " + name + ", Age: " + age + ", Average Grade: " + calculateAverageGrade();
    }

    public static class Course {
        private String courseName;
        private String courseCode;

        public Course(String courseName, String courseCode) {
            this.courseName = courseName;
            this.courseCode = courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getCourseCode() {
            return courseCode;
        }
    }
}

public class UndergraduateStudent extends Student {
    public UndergraduateStudent(String name, int age, int studentId, int[] grades) {
        super(name, age, studentId, grades);
    }

    @Override
    public String getDetails() {
        return "Undergraduate Student - " + super.getDetails();
    }
}

public class GraduateStudent extends Student {
    public GraduateStudent(String name, int age, int studentId, int[] grades) {
        super(name, age, studentId, grades);
    }

    @Override
    public String getDetails() {
        return "Graduate Student - " + super.getDetails();
    }
}

public class StudentManagementSystem {
    private Student[] students;
    private int studentCount;

    public StudentManagementSystem(int capacity) {
        students = new Student[capacity];
        studentCount = 0;
    }

    public void addStudent(Student student) {
        if (studentCount < students.length) {
            students[studentCount++] = student;
        } else {
            System.out.println("No more capacity to add new students.");
        }
    }

    public void displayAllStudents() {
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].getDetails());
        }
    }

    public void displayScholarshipEligibleStudents() {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].isEligibleForScholarship()) {
                System.out.println(students[i].getDetails());
            }
        }
    }
}

public class ReportGenerator implements Runnable {
    private StudentManagementSystem sms;

    public ReportGenerator(StudentManagementSystem sms) {
        this.sms = sms;
    }

    @Override
    public void run() {
        System.out.println("Generating report...");
        sms.displayAllStudents();
    }
}

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem(10);

        Student student1 = new UndergraduateStudent("Alice", 20, 1, new int[]{90, 85, 88});
        Student student2 = new GraduateStudent("Bob", 24, 2, new int[]{92, 87, 90});
        Student student3 = new UndergraduateStudent("Charlie", 19, 3, new int[]{70, 75, 78});

        sms.addStudent(student1);
        sms.addStudent(student2);
        sms.addStudent(student3);

        System.out.println("All Students:");
        sms.displayAllStudents();

        System.out.println("\nScholarship Eligible Students:");
        sms.displayScholarshipEligibleStudents();

        ReportGenerator reportGenerator = new ReportGenerator(sms);
        Thread reportThread = new Thread(reportGenerator);
        reportThread.start();
    }
}



