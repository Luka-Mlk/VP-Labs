package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>(
            Arrays.asList(new Book("The Hobbit", "Fantasy", 4.8),
                    new Book("1984", "Dystopian", 4.7),
                    new Book("To Kill a Mockingbird", "Classic", 4.9),
                    new Book("The Catcher in the Rye", "Classic", 4.3),
                    new Book("The Da Vinci Code", "Thriller", 4.5),
                    new Book("The Shining", "Horror", 4.6),
                    new Book("Pride and Prejudice", "Romance", 4.9),
                    new Book("Dune", "Science Fiction", 4.8),
                    new Book("The Alchemist", "Philosophical", 4.4),
                    new Book("Murder on the Orient Express", "Mystery", 4.6)
            )
    );
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        books = new ArrayList<>();
        reservations = new ArrayList<>();

        books.addAll(Arrays.asList(new Book("The Hobbit", "Fantasy", 4.8),
                new Book("1984", "Dystopian", 4.7),
                new Book("To Kill a Mockingbird", "Classic", 4.9),
                new Book("The Catcher in the Rye", "Classic", 4.3),
                new Book("The Da Vinci Code", "Thriller", 4.5),
                new Book("The Shining", "Horror", 4.6),
                new Book("Pride and Prejudice", "Romance", 4.9),
                new Book("Dune", "Science Fiction", 4.8),
                new Book("The Alchemist", "Philosophical", 4.4),
                new Book("Murder on the Orient Express", "Mystery", 4.6))
        );
    }
}
