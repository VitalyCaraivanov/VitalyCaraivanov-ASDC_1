package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StudentBinaryTree {
    private Node rootOfTree;

    public StudentBinaryTree() {
        this.rootOfTree = null;
    }

    public void insert(Student student) {
        rootOfTree = insertHelper(rootOfTree, student);
    }

    public void insertStudentFromArrayList(@NotNull ArrayList<Student> studentArrayList) {
        for (Student student : studentArrayList)
            insert(student);
    }

    public void showTree() {
        showTreeHelper(rootOfTree);
    }

    public Node getRootOfTree() {
        return rootOfTree;
    }

    private void showTreeHelper(Node node) {
        if (node == null)
            return;
        System.out.println(node.student);
        showTreeHelper(node.right);
        showTreeHelper(node.left);
    }


    private Node insertHelper(Node node, Student student) {
        if (node == null)
            return new Node(student);
        if (student.getId() < node.student.getId())
            node.left = insertHelper(node.left, student);
        else if (student.getId() > node.student.getId())
            node.right = insertHelper(node.right, student);
        else
            return rootOfTree; // если элемент уже присутствует в дереве
        return node;
    }


    static class Node {
        private Node left;
        private Node right;
        private Student student;

        public Node(Student student) {
            this.left = this.right = null;
            this.student = student;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Student getStudent() {
            return student;
        }
    }
}
