package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Component;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Author> authors = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        reservations = new ArrayList<>();

        authors.add(new Author("Gabriel", "García Márquez", "Colombia", "Biography 1: A Nobel Prize-winning author, known for 'One Hundred Years of Solitude'."));
        authors.add(new Author("J.R.R.", "Tolkien", "United Kingdom", "Biography 2: The creator of the fantasy world of Middle-earth, author of 'The Lord of the Rings'."));
        authors.add(new Author("Jane", "Austen", "United Kingdom", "Biography 3: A prominent English novelist whose works critique the British landed gentry."));
    }
}
