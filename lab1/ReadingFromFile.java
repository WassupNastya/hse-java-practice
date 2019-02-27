package lessons.lesson3;

import java.io.*;
import java.util.Scanner;

public class ReadingFromFile {
    public void readFromFile(FileWriter fileWriter) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String inputFile = in.nextLine();

        try {
            FileInputStream fileStream = new FileInputStream(inputFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
            String strLine;
            int countStrings = 0;
            try {
                while ((strLine = bufferedReader.readLine()) != null) {
                    countStrings++;
                    LineProcessing ln = new LineProcessing(strLine);
                    fileWriter.write(ln.toString() + '\n');
                }
                    bufferedReader.close();
                    fileStream.close();
                    fileWriter.close();
                }
                catch (InvalidLineException e) { System.out.println("Error in input data in line " + countStrings); }
                //catch (NullPointerException e) { System.out.println("Error in input data in line " + countStrings); }
                //catch (NumberFormatException e){ System.out.println("Error in input data in line " + countStrings); }
        }
        catch (IOException e){ System.out.println("Cannot read from file"); }
    }
}
