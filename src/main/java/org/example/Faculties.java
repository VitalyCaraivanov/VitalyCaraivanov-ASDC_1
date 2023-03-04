package org.example;

public enum Faculties {
    FACULTY_OF_MATHEMATICS_AND_COMPUTER_SCIENCE(1),
    FACULTY_OF_ECONOMIC_SCIENCES(2),
    FACULTY_OF_HISTORY_AND_PHILOSOPHY(3),
    FACULTY_OF_PHILOLOGY(4),
    FACULTY_OF_BIOLOGY_AND_SOIL_SCIENCE(5),
    FACULTY_OF_LAW(6);

    private final int ID;

    Faculties(int id) {
        this.ID = id;
    }

    public static Faculties getFaculty(Student student) {
        for (Faculties faculty : Faculties.values()) {
            if (faculty.ordinal() + 1 == student.getFaculty())
                return faculty;
        }
        return null;
    }
}
