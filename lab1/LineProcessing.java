package lessons.lesson3;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class LineProcessing {
    private String string;
    private String surname;
    private String name;
    private String patronymic;
    private String date;
    private String gender;
    private int age;

    @Override
    public String toString() {
        return this.surname + " " + this.name.charAt(0) + ". " + this.patronymic.charAt(0) +
                ". " + this.gender + " " + this.age;
    }

    public LineProcessing(String string) { this.string = string; }

    public void processLine() throws InvalidLineException {
        String[] tmp = new String[4];
        int counter = 0;

        for (String data : this.string.split(" ")) {
            tmp[counter] = data;
            counter++;
        }

        try {
            surname = tmp[0].toLowerCase();
            name = tmp[1].toLowerCase();
            patronymic = tmp[2].toLowerCase();
            date = tmp[3];

            surname = Character.toUpperCase(surname.charAt(0)) + surname.substring(1);
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            patronymic = Character.toUpperCase(patronymic.charAt(0)) + patronymic.substring(1);

            setGender();
            setAge();
        }
        catch (NullPointerException e) {
            throw new InvalidLineException("Error in surname, name or patronymic");
        }
    }

    private void setGender() {
        if (patronymic.charAt(patronymic.length() - 1) == 'а') gender = "женский";
        else if (patronymic.charAt(patronymic.length() - 1) == 'ч') gender = "мужской";
        else gender = "не определен";
    }

    private void setAge() throws InvalidLineException {
        String[] tmp = new String[3];
        int counter = 0;

        for (String data : this.date.split(Pattern.quote("."))) {
            tmp[counter] = data;
            counter++;
        }

        LocalDate date;
        LocalDate now = LocalDate.now();

        try {
            date = LocalDate.of(Integer.parseInt(tmp[2]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[0]));
        }
        catch (Exception e) { throw new InvalidLineException("Error in date of birth"); }


        if (date.getDayOfYear() < now.getDayOfYear()) { age = now.getYear() - date.getYear() - 1; }
        else { age = now.getYear() - date.getYear();}

        if (age < 0) throw new InvalidLineException("Age cannot be lower 0");
        }

}
