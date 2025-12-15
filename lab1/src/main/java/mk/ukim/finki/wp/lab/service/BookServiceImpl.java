package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id);
    }

    public Book create(String title, String genre, double averageRating, Long authorId) {
        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with ID " + authorId));

        Book book = new Book(title, genre, averageRating, author);
        return bookRepository.save(book);
    }

    public Book update(Long id, String title, String genre, Double averageRating, Long authorId) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID " + id));

        var author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found with ID " + authorId));

        existingBook.setTitle(title);
        existingBook.setGenre(genre);
        existingBook.setAverageRating(averageRating);
        existingBook.setAuthor(author);

        return bookRepository.save(existingBook);
    }

    public String delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Book not found with ID " + id);
        }
        bookRepository.deleteById(id);
        return "OK";
    }

    public List<Book> searchBooks(String text, Double rating) {
        return bookRepository.findByTitleContainingAndAverageRatingGreaterThanEqual(text, rating);
    }

    public List<Book> findAllByAuthor_Id(Long authorId) {
        return bookRepository.findAllByAuthor_Id(authorId);
    }
}
