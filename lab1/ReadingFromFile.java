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
                    ln.setInformation();
                    fileWriter.write(ln.toString() + '\n');
                }
            }
            catch (InvalidLineException e) { System.out.println("Error in input data in line " + countStrings); }
            finally {
                bufferedReader.close();
                fileStream.close();
                fileWriter.close();
            }
        }
        catch (IOException e) { System.out.println("File Error"); }
    }
}
