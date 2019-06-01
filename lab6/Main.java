package lessons.lesson9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student("Иванов", "Иван", 6));
        students.add(new Student("Смирнов", "Денис", 8));
        students.add(new Student("Сидоров", "Константин", 5));
        students.add(new Student("Кузнецова", "Алина", 9));
        students.add(new Student("Михайлова", "Ангелина", 10));
        students.add(new Student("Алексеев", "Андрей", 4));
        students.add(new Student("Егоров", "Алексей", 8));
        students.add(new Student("Павлова", "Виолетта", 4));
        students.add(new Student("Козлов", "Альберт", 6));
        students.add(new Student("Новиков", "Виктор", 7));
        students.add(new Student("Соколова", "Варвара", 7));


        List<Student> scholarshipStudents = students.stream()
                .filter(student -> student.getBall() >= 6)
                .sorted(Comparator.comparing(Student::getSurname))
                .collect(Collectors.toList());

        scholarshipStudents.forEach(student -> System.out.println(student));

    }
}
