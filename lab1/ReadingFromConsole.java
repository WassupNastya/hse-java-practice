package lessons.lesson3;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReadingFromConsole {
    public void readFromConsole(FileWriter fileWriter) {
        Scanner in = new Scanner(System.in);
        System.out.println("To stop input exit");
        String string;
        int countStrings = 0;
        try {
            do {
                string = in.nextLine();
                if (!string.equals("exit")) {
                    countStrings++;
                    LineProcessing ln = new LineProcessing(string);
                    fileWriter.write(ln.toString() + '\n');
                }
            } while (!string.equals("exit"));
            fileWriter.close();
        }
        catch (InvalidLineException e) { System.out.println("Error in input data in line " + countStrings); }
        //catch(NullPointerException e) { System.out.println("Error in input data in line " + countStrings); }
        //catch (NumberFormatException e){ System.out.println("Error in input data in line " + countStrings); }
        catch(IOException e) {System.out.println("Error writing to file."); }
    }
}
