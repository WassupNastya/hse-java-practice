package lessons.lesson3;

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter f to read from file or c to write in console: ");
        String input = in.nextLine();

        try {
            FileWriter fileWriter = new FileWriter("listID.txt");
            switch(input) {
                case "f":
                   ReadingFromFile rff = new ReadingFromFile();
                   rff.readFromFile(fileWriter);
                   break;
                case "c":
                    ReadingFromConsole rfc = new ReadingFromConsole();
                    rfc.readFromConsole(fileWriter);
                    break;

                default:
                    fileWriter.close();
                    throw new InvalidInputDataException();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Cannot open file to write");
        }
        catch (InvalidInputDataException e) {
           System.out.println("Invalid key!");
        }
    }
}
