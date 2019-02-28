package lessons.lesson3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("Input surname, name, patronymic and date of birth (dd.mm.yyyy)");

        Scanner in = new Scanner(System.in);
        String string;

        try {
            FileWriter fileWriter = new FileWriter("listID.txt");
            try {
                string = in.nextLine();
                LineProcessing ln = new LineProcessing(string);
                ln.processLine();
                fileWriter.write(ln.toString() + '\n');

            }
            catch (InvalidLineException e) { System.out.println("Invalid input data: " + e.getMessage()); }
            finally { fileWriter.close(); }
        }
        catch(IOException e) {System.out.println("Error to write in file"); }
    }
}
