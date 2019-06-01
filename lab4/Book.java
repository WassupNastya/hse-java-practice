package lessons.lesson4;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String author;
    private String language;
    private int numberOfPages;

    public Book () {
        this.title = "default title";
        this.author = "default author";
        this.language = "default language";
        this.numberOfPages = 0;
    }

    public Book (String title, String author, String language, int numberOfPages) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    public static Book BookConsoleInput(Scanner in) {
        Book book = new Book();

        System.out.println("Enter title of the book: ");
        book.setTitle(in.nextLine());

        System.out.println("Enter author: ");
        book.setAuthor(in.nextLine());

        System.out.println("Enter language: ");
        book.setLanguage(in.nextLine());

        try {
            System.out.println("Enter number of pages: ");
            book.setNumberOfPages(Integer.parseInt(in.nextLine()));
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong input format!");
        }

        return book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(language, book.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, language, numberOfPages);
    }

    @Override
    public String toString() {
        return ('"' + title + '"' + ' ' + author + ' ' + language + ' ' + numberOfPages + " pages\n");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


}
