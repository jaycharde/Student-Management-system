This project is a simple Student Management System implemented in Java.
It demonstrates the use of abstract classes, inheritance, interfaces, static variables, inner classes, polymorphism, arrays, and multithreading.
Abstract Class:

Person is an abstract class with attributes name and age, and an abstract method getDetails().

Inheritance:

Student extends Person and includes additional attributes studentId and grades, with a method to calculate the average grade.

Interface:

ScholarshipEligibility interface with a method isEligibleForScholarship().

Static Keyword:

Student class uses a static variable totalStudents to track the total number of students and a static method getTotalStudents() to access this count.

Inner Class:

Course is a nested class inside Student representing the courses enrolled by the student.

Polymorphism:

UndergraduateStudent and GraduateStudent extend Student and override getDetails() to include specific information about the level of education.

Arrays:

StudentManagementSystem class stores the list of students in an array and performs operations such as adding a student, displaying all students, and finding students eligible for scholarships.

Multithreading:

ReportGenerator class implements Runnable and generates a report of all students and their average grades in a separate thread.
