package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SingletonWorkerClass singletonWorker = SingletonWorkerClass.getInstance();
        ArrayList<Student> studentArrayList = singletonWorker.readFromCSVFile();
        singletonWorker.showStudentArrayList(studentArrayList);

        if (singletonWorker.isSortedDataRead()) {
            Student linearStudent = singletonWorker.linearSearchByKey(studentArrayList, "lastname");
            System.out.println(linearStudent);

            Student binaryStudent = singletonWorker.binarySearchById(studentArrayList, 36);
            System.out.println(binaryStudent);

            Student interpolatingStudent = singletonWorker.interpolatingSearchById(studentArrayList, 40);
            System.out.println(interpolatingStudent);


            Student fibonacciStudent = singletonWorker.fibonacciSearchById(studentArrayList, 50);
            System.out.println(fibonacciStudent);
        } else {

            Student student = singletonWorker.linearSearchByKey(studentArrayList, "lastname");
            System.out.println(student);

            System.out.println(singletonWorker.binaryTreeSearch(studentArrayList, 49));

        }

    }


}