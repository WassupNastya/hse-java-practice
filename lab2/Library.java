package lessons.lesson4;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;

    HashMap<String, Book> library = new HashMap<>();

    public void add(String id, Book book) {
        library.put(id, book);
    }

    public void delete(String id) {
        Book tmp = library.remove(id);
        if (tmp == null) System.out.println("No item with this id");
        else System.out.println(id + " is successfully deleted");
    }

    public String find(String id) {
        try {
            return library.get(id).toString();
        }
        catch (NullPointerException e) {
            return ("Sorry, item is not found");
        }
    }

    public boolean edit(String id, BookTypes type, Scanner in) {
        System.out.println("Enter new data: ");
        String data = in.nextLine();
        if (type == BookTypes.TITLE) library.get(id).setTitle(data);
        if (type == BookTypes.AUTHOR) library.get(id).setAuthor(data);
        if (type == BookTypes.LANGUAGE) library.get(id).setLanguage(data);
        try {
            if (type == BookTypes.PAGES) library.get(id).setNumberOfPages(Integer.parseInt(data));
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong input format!");
            return false;
        }
        return true;
    }

    public String get(String id) {
        if (library.get(id) == null) return null;
        else return library.get(id).toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = library.keySet().iterator();
        while (it.hasNext()) {
            String tmp = it.next();
            String str = library.get(tmp).toString();
            sb.append("id: ").append(tmp).append(" ").append(str);
        }
        return sb.toString();
    }

}
