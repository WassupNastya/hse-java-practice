package lessons.lesson3;
import java.util.Calendar;
import java.util.regex.Pattern;

public class LineProcessing {
    private String string;
    private String surname;
    private String name;
    private String patronymic;
    private String date;
    private String gender;
    private int age;

    public LineProcessing(String string) throws InvalidLineException {
        this.string = string;
        try {
            this.setFields();
        }
        catch (NullPointerException e) {
            throw new InvalidLineException();
        }
        catch (NumberFormatException e) {
            throw new InvalidLineException();
        }
    }

    private String[] separateString() {
        String[] tmp = new String[4];
        int counter = 0;

        for (String data : this.string.split(" ")) {
            tmp[counter] = data;
            counter++;
        }
        return tmp;
    }

    private void setFields() throws NullPointerException {
        String[] tmp = separateString();
        try {
            this.surname = tmp[0];
            this.name = tmp[1];
            this.patronymic = tmp[2];
            this.date = tmp[3];

            setGender();
            setAge();
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    private void setGender() {
        if (patronymic.charAt(patronymic.length() - 1) == 'а') gender = "женский";
        else if (patronymic.charAt(patronymic.length() - 1) == 'ч') gender = "мужской";
        else gender = "не определен";
    }

    private void setAge() throws NullPointerException, NumberFormatException {
        String[] tmp = new String[3];
        int counter = 0;
        for (String data : this.date.split(Pattern.quote("."))) {
            tmp[counter] = data;
            counter++;
        }

        try {
            String d = tmp[0];
            String m = tmp[1];
            String y = tmp[2];

            try {
                int day = Integer.parseInt(d);
                int month = Integer.parseInt(m);
                int year = Integer.parseInt(y);
                Calendar rightNow = Calendar.getInstance();
                Calendar receivedDate = Calendar.getInstance();
                receivedDate.set(year, month, day);

                if (rightNow.after(receivedDate)) age = rightNow.getWeekYear() - year - 1;
                else age = rightNow.getWeekYear() - year;

                if (age < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                throw e;
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    @Override
    public String toString() {
        return this.surname + " " + this.name.charAt(0) + ". " + this.patronymic.charAt(0) +
                ". " + this.gender + " " + this.age;
    }
}
