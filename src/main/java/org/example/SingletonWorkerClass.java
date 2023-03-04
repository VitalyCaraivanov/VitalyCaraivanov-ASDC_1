package org.example;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SingletonWorkerClass {
    private final Scanner SCANNER = new Scanner(System.in);
    private static SingletonWorkerClass singletonWorkerClass = null;
    private static final File sortedFile = new File
            ("D:\\JavaFiles\\ASDC_1\\src\\main\\java\\org\\example\\data.txt");
    private static final File unsortedFile = new File
            ("D:\\JavaFiles\\ASDC_1\\src\\main\\java\\org\\example\\unsorted.txt");
    private boolean sortedDataRead;

    static SingletonWorkerClass getInstance() {
        return Objects.requireNonNullElseGet(singletonWorkerClass, () -> (singletonWorkerClass =
                new SingletonWorkerClass()));
    }

    public boolean isSortedDataRead() {
        return sortedDataRead;
    }

    public ArrayList<Student> readFromCSVFile() {
        ArrayList<Student> studentArrayList;
        int choice = 0;
        System.out.println("Выберите из какого файла считать данные:\n 1 - из отсортированного\n 2 - из неотсортированного");
        do {
            choice = Integer.parseInt(SCANNER.nextLine());
        } while (choice != 1 && choice != 2);

        switch (choice) {
            case 1 -> {
                studentArrayList = readFromFile(sortedFile);
                sortedDataRead = true;
                return studentArrayList;
            }
            case 2 -> {
                studentArrayList = readFromFile(unsortedFile);
                return studentArrayList;
            }
        }
        return null;
    }

    public void showStudentArrayList(@NotNull ArrayList<Student> studArrayList) {
        for (Student student : studArrayList) {
            for (Faculties faculty : Faculties.values()) {
                if (student.getFaculty() == faculty.ordinal() + 1)
                    if (faculty.ordinal() + 1 == student.getFaculty()) {
                        System.out.println(student.getId() + " "
                                + student.getName() + " "
                                + student.getSurname() + " "
                                + student.getYearOfBirth() + " "
                                + student.getYearOfIntroduction() + " "
                                + " " + faculty + "\t" + student.getFaculty());
                        break;
                    }
            }
        }
    }

    private ArrayList<Student> readFromFile(File file) {
        ArrayList<Student> studentArrayList = new ArrayList<>();
        String readLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            readLine = bufferedReader.readLine();

            while ((readLine = bufferedReader.readLine()) != null) {
                String[] splittedString = readLine.split(",");
                studentArrayList.add(
                        new Student(
                                Integer.parseInt(splittedString[0]),
                                splittedString[1],
                                splittedString[2],
                                Integer.parseInt(splittedString[3]),
                                Integer.parseInt(splittedString[4]),
                                Integer.parseInt(splittedString[5])
                        )
                );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return studentArrayList;
    }

    public Student linearSearchByKey(@NotNull ArrayList<Student> arrayList, String key) {
        String keyUpper = key.toUpperCase();
        if (keyUpper.equals("ID")) {
            int id;
            System.out.println("Введите искомый ID, чтобы получить студента");
            id = Integer.parseInt(SCANNER.nextLine());
            SCANNER.close();
            return linearSearchById(arrayList, id);
        }
        if (keyUpper.equals("FIRSTNAME")) {
            System.out.println("Введите имя, по которому произойдет поиск");
            String firstName = SCANNER.nextLine();
            return linearSearchByFirstName(arrayList, firstName);
        }
        if (keyUpper.equals("LASTNAME")) {
            System.out.println("Введите фамилию, по которой произойдет линейный поиск");
            String lastName = SCANNER.nextLine();
            return linearSearchByLastName(arrayList, lastName);
        }

        return null; // if key is invalid
    }

    private Student linearSearchById(@NotNull ArrayList<Student> studentArrayList, int id) {
        long startTimeMillis = System.nanoTime();
        long afterSearchMillisTime;
        System.out.println("Start time " + startTimeMillis);
        for (Student student : studentArrayList) {
            if (student.getId() == id) {
                afterSearchMillisTime = System.nanoTime();
                System.out.println("End time: " + afterSearchMillisTime);
                System.out.println("Time of execution: " + (afterSearchMillisTime - startTimeMillis));
                return student;
            }
        }
        afterSearchMillisTime = System.nanoTime();
        System.out.println("End time: " + afterSearchMillisTime);
        System.out.println("Time of execution: " + (afterSearchMillisTime - startTimeMillis) + " nsec");

        return null; // if we didn't find a student
    }

    private Student linearSearchByFirstName(@NotNull ArrayList<Student> studentArrayList, String firstName) {
        long startTimeMillis = System.currentTimeMillis();
        long afterSearchMillisTime;
        System.out.println("Start time " + startTimeMillis);
        for (Student student : studentArrayList) {
            if (student.getName().equalsIgnoreCase(firstName)) {
                afterSearchMillisTime = System.currentTimeMillis();
                System.out.println("End time " + afterSearchMillisTime);
                System.out.println("Time of execution: " + (afterSearchMillisTime - startTimeMillis));

                return student;
            }
        }
        afterSearchMillisTime = System.currentTimeMillis();
        System.out.println("End time: " + afterSearchMillisTime);
        System.out.println("Time of execution: " + (afterSearchMillisTime - startTimeMillis));
        return null; // if we didn't find a student
    }

    private Student linearSearchByLastName(@NotNull ArrayList<Student> studentArrayList, String lastName) {
        long startTimeMillis = System.currentTimeMillis();
        long afterSearchMillisTime;
        System.out.println("Start time " + startTimeMillis);
        for (Student student : studentArrayList) {
            if (student.getSurname().equalsIgnoreCase(lastName)) {
                afterSearchMillisTime = System.currentTimeMillis();
                System.out.println("End time " + afterSearchMillisTime);
                System.out.println("Time of execution: " + ((afterSearchMillisTime - startTimeMillis)));

                return student;
            }
        }
        afterSearchMillisTime = System.currentTimeMillis();
        System.out.println("Time of execution: " + (afterSearchMillisTime - startTimeMillis));

        return null; // if we didn't find a student
    }

    public Student binarySearchById(@NotNull ArrayList<Student> sortedArrayList, int id) {
        System.out.println("Binary search method");
        int first = 0;
        int last = sortedArrayList.size() - 1, middle;
        long startTimeMillis = System.currentTimeMillis(), afterSearchTimeMillis;
        System.out.println("Current time " + startTimeMillis);
        while (first <= last) {
            middle = (first + last) / 2;
            if (sortedArrayList.get(middle).getId() == id) {
                afterSearchTimeMillis = System.currentTimeMillis();
                System.out.println("End time = " + afterSearchTimeMillis);
                System.out.println("Execution time = " + (afterSearchTimeMillis - startTimeMillis));
                return sortedArrayList.get(middle);
            } else if (sortedArrayList.get(middle).getId() < id)
                first = middle + 1;
            else
                last = middle - 1;
        }
        afterSearchTimeMillis = System.currentTimeMillis();
        System.out.println("Execution time = " + (afterSearchTimeMillis - startTimeMillis));
        return null;
    }

    public Student interpolatingSearchById(@NotNull ArrayList<Student> sortedStudentArrayList, int idToFind) {
        System.out.println("Interpolation search method");
        int low = 0, high = sortedStudentArrayList.size() - 1, mid;

        while (sortedStudentArrayList.get(low).getId() < idToFind &&
                sortedStudentArrayList.get(high).getId() > idToFind) {
            if (sortedStudentArrayList.get(low).getId() == sortedStudentArrayList.get(high).getId())
                break;
            mid = low + ((idToFind - sortedStudentArrayList.get(low).getId()) * (high - low))
                    / (sortedStudentArrayList.get(high).getId() - sortedStudentArrayList.get(low).getId());
            if (sortedStudentArrayList.get(mid).getId() == idToFind) {
                return sortedStudentArrayList.get(mid);
            } else if (sortedStudentArrayList.get(mid).getId() < idToFind)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return null;
    }

    private long getFibonacciNumber(int k) {
        long[] FibonacciArray = new long[k + 1];
        FibonacciArray[0] = 0;
        FibonacciArray[1] = 1;
        for (int i = 2; i < FibonacciArray.length; i++) {
            FibonacciArray[i] = FibonacciArray[i - 1] + FibonacciArray[i - 2];
        }

        return FibonacciArray[k];
    }

    public Student fibonacciSearchById(@NotNull ArrayList<Student> sortedStudentList, int id) {
        System.out.println("Fibonacci search method");
        boolean stop = false;
        int k = 0;
        while (getFibonacciNumber(k + 1) < sortedStudentList.size() + 1)
            k++;
        System.out.println(k);
        int m = (int) (getFibonacciNumber(k + 1) - (sortedStudentList.size() + 1));
        int i = (int) (getFibonacciNumber(k) - m);
        int p = (int) getFibonacciNumber(k - 1);
        int q = (int) getFibonacciNumber(k - 2);

        long currentTimeNanos = System.nanoTime(), afterSearchTimeNanos;
        System.out.println("Start time " + currentTimeNanos);

        while (!stop) {
            if (i < 0) {
                if (p == 1) {
                    stop = true;
                }
                i = i + q;
                p = p - q;
                q = q - p;
            } else if (i >= sortedStudentList.size()) {
                if (q == 0) {
                    stop = true;
                }
                i = i - q;
                int temp = q;
                q = p - q;
                p = temp;
            } else if (sortedStudentList.get(i).getId() == id) {
                afterSearchTimeNanos = System.nanoTime();
                System.out.println("End time " + afterSearchTimeNanos + "\nExecution time = " +
                        (afterSearchTimeNanos - currentTimeNanos));
                return sortedStudentList.get(i);
            } else if (id < sortedStudentList.get(i).getId()) {
                if (q == 0) {
                    stop = true;
                }
                i = i - q;
                int temp = q;
                q = p - q;
                p = temp;
            } else if (id > sortedStudentList.get(i).getId()) {
                if (p == 1) {
                    stop = true;
                }
                i = i + q;
                p = p - q;
                q = q - p;
            }
        }
        afterSearchTimeNanos = System.nanoTime();
        System.out.println("End time " + afterSearchTimeNanos + "\nExecution time = " +
                (afterSearchTimeNanos - currentTimeNanos));
        return null; // if such student doesn't exist
    }

    public Student binaryTreeSearch(@NotNull ArrayList<Student> unsortedStudentList, int id) {

        if (sortedDataRead)
            return null;

        StudentBinaryTree studentTree = new StudentBinaryTree();
        studentTree.insertStudentFromArrayList(unsortedStudentList);

        long startTime = System.nanoTime(), endTime;
        System.out.println("Start time " + startTime);

        StudentBinaryTree.Node currentNode = studentTree.getRootOfTree();
        if (currentNode.getStudent().getId() == id)
            return currentNode.getStudent();

        while (currentNode != null && currentNode.getStudent().getId() != id) {

            if (id < currentNode.getStudent().getId()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
            if (currentNode != null && currentNode.getStudent().getId() == id) {
                endTime = System.nanoTime();
                System.out.println("End time " + endTime);
                System.out.println("Execution time: " + (endTime - startTime) + " nsec");
                return currentNode.getStudent();
            }
        }

        return null; // if such student doesn't exist
    }

}
