package lessons.lesson4;

import java.io.*;
import java.util.Scanner;
public class MenuLibrary  {

    public static void find(Scanner in, Library library) {
        System.out.println("Enter id: ");
        System.out.println(library.find(in.nextLine()));
    }

    public static void delete(Scanner in, Library library) {
        System.out.println("Enter id: ");
        library.delete(in.nextLine());
    }

    public static void add(Scanner in, Library library) {
        Book book = Book.BookConsoleInput(in);
        System.out.println("Enter id: ");
        try {
            String id = in.nextLine();
            if (library.get(id) == null) { library.add(id, book); }
            else {
                System.out.println("Item with the id " + id + " already exists");
                System.out.println("1. Rewrite the id with new item. Previous item will be deleted");
                System.out.println("2. Choose another id");
                switch(in.nextLine()) {
                    case "1":
                        library.add(id, book);
                        break;
                    case "2":
                        add(in, library);
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Wrong input data");
        }
    }

    public static void edit(Scanner in, Library library) {
        try {
            System.out.println("Enter id: ");
            String id = in.nextLine();
            System.out.println("1. Edit title");
            System.out.println("2. Edit author");
            System.out.println("3. Edit language");
            System.out.println("4. Edit number of pages");
            switch (in.nextLine()) {
                case "1":
                    library.edit(id, BookTypes.TITLE, in);
                    break;
                case "2":
                    library.edit(id, BookTypes.AUTHOR, in);
                    break;
                case "3":
                    library.edit(id, BookTypes.LANGUAGE, in);
                    break;
                case "4":
                    library.edit(id, BookTypes.PAGES, in);
                    break;
                    default:
                        throw new IllegalArgumentException();
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Wrong input data");
        }
    }

    public static void serialize(Library library, String filename) {
        try (FileOutputStream fs = new FileOutputStream(filename); ObjectOutputStream os = new ObjectOutputStream(fs)) {
            os.writeObject(library);
        }
        catch (IOException e) {
            System.out.println("File Writing error");
        }
    }

    public static Library deserialize(String filename) {
        File file = new File(filename);
        Library library = new Library();
        try (FileInputStream fs = new FileInputStream(file); ObjectInputStream os = new ObjectInputStream(fs)) {
            try {
                library = (Library)os.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("Error with serialization");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }
        catch (IOException e) {
            System.out.println("File Reading Error");
        }
        return library;
    }

    public static void showAll(Library library) {
        System.out.println(library.toString());
    }

    public static void writeToFile(Library library) {
        try (FileWriter fileWriter = new FileWriter("LibraryDatabase.txt")) {
            fileWriter.write(library.toString());
        }
        catch (IOException e) {
            System.out.println("Error to write into file");
        }
    }
}
