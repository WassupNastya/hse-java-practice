package lessons.lesson9;

public class Student  {
    private String name;
    private String surname;
    private int ball;

    public Student(String surname, String name, int ball) {
        this.name = name;
        this.surname = surname;
        this.ball = ball;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBall() {
        return ball;
    }

    @Override
    public String toString() {
        return (surname + " " + name + " ball: " + ball);
    }
}

