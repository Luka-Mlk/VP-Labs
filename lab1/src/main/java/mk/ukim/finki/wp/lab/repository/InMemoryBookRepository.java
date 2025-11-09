package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryBookRepository implements BookRepository {
    final private AuthorRepository authorRepository;

    public InMemoryBookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return DataHolder.books;
    };

    public List<Book> searchBooks(String text, Double rating) {
        return DataHolder.books.stream().filter(b -> b.getTitle().contains(text) && b.getAverageRating() > rating).toList();
    };

    public Book getById(Long id) {
        return DataHolder.books.stream()
                .filter(b -> b.getId().equals(id)).findFirst().orElse(null);
    };

    public Book update(Long id, String title, String genre, Double averageRating, Long authorId) {
        Author bookAuthor = authorRepository.findById(authorId).getFirst();
        Book bookToUpdate = this.getById(id);
        bookToUpdate.setId(id);
        bookToUpdate.setTitle(title);
        bookToUpdate.setGenre(genre);
        bookToUpdate.setAverageRating(averageRating);
        bookToUpdate.setAuthor(bookAuthor);
        return bookToUpdate;
    }

    public Book create(String title, String genre, double averageRating, Long authorId) {
        System.out.println(authorId);
        Author bookAuthor = authorRepository.findById(authorId).getFirst();
        Book newBook = new Book(title, genre, averageRating, bookAuthor);
        DataHolder.books.add(newBook);
        return newBook;
    }

    public String delete(Long id) {
        DataHolder.books.removeIf(b -> b.getId().equals(id));
        return "OK";
    }
}
