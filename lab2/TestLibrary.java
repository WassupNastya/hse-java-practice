package lessons.lesson4;

import java.util.Scanner;

public class TestLibrary {
    public static void main(String[] args) {
        String filename = "Library.txt";
        Library library = new Library();

        Scanner in = new Scanner(System.in);
        int choice = 0;
        while (choice != 9) {
            System.out.println("\nLibrary");
            System.out.println("1. Add book");
            System.out.println("2. Delete book");
            System.out.println("3. Find book");
            System.out.println("4. Edit book");
            System.out.println("5. Serialize Library into file");
            System.out.println("6. Serialize Library from file");
            System.out.println("7. Show all items in library");
            System.out.println("8. Write to file");
            System.out.println("9. Exit");
            System.out.println("Enter a number: ");
            try {
                choice = Integer.parseInt(in.nextLine());
                if ((choice < 1) || (choice > 9)) throw new NumberFormatException();

                switch (choice) {
                    case 1:
                        MenuLibrary.add(in, library);
                        break;
                    case 2:
                        MenuLibrary.delete(in, library);
                        break;
                    case 3:
                        MenuLibrary.find(in, library);
                        break;
                    case 4:
                        MenuLibrary.edit(in, library);
                        break;
                    case 5:
                        MenuLibrary.serialize(library, filename);
                        break;
                    case 6:
                        library = MenuLibrary.deserialize(filename);
                        break;
                    case 7:
                        MenuLibrary.showAll(library);
                        break;
                    case 8:
                        MenuLibrary.writeToFile(library);
                        break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Wrong input data. Try again");
            }
        }
    }
}
