package com.rajat;

import javax.swing.plaf.synth.SynthRadioButtonUI;
import java.util.Scanner;

public class MyLauncher {

    public static void main(String[] args) {
        System.out.println("*************************************");
        System.out.println("Welcome to the School Admission App!   Press X for exit!!!");
        System.out.println("*************************************");

        System.out.println("Enter name of student: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        if(name.equals("x") || name.equals("X")){
            System.exit(0);
        }
        while (name.length() >= 50 || name.length() <3){
            System.out.println("Please enter a valid name!");
            System.out.println("Enter name of student: ");
            name = scanner.nextLine();
        }
        System.out.println("Enter age of student :");
        Integer age = scanner.nextInt();

        while (age <4 || age >17){
            System.out.println("Please enter a valid age!");
            System.out.println("Enter age of student :");
            age = scanner.nextInt();
        }
        System.out.println("Student name is : " +name+ " and Student age is : " +age);

        // With help of age define the grade of student
        GradeType gradeType = null;
        try{
            gradeType = UtilityClass.determineGradeBasedOnAge(age);
        }catch (AgeNotCorrectException e) {
            System.out.println("Age has to be between 4 and 17");
        }
        // Determining school type
        SchoolType schoolType = null;
        try{
            schoolType = UtilityClass.determineSchoolBasedOnGrade(gradeType);
        } catch (NoSchoolAvailableForThisAgeException e) {
            System.out.println("School not found for this grade!");
        }

        Student student = new Student(name, age, gradeType);
        School school = null;
        try {
            school = UtilityClass.retrieveSchoolObjectBasedOnSchoolType(schoolType);
            school.admitStudent(name, age, gradeType);
        } catch (ClassFullException e) {
            System.out.println("Sorry the class for grade #" + gradeType + " is full, please try another student");
        }
        System.out.println("Welcome ... ");

    }
}
