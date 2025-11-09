package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    public final BookRepository bookRepository;

    public BookServiceImpl(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }

    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    public Book create(String title, String genre, double averageRating, Long authorId) {
        return this.bookRepository.create(title, genre, averageRating, authorId);
    }

    public Book getById(Long id) {
        return this.bookRepository.getById(id);
    }

    public Book update(Long id, String title, String genre, Double averageRating, Long authorId) {
        return bookRepository.update(id, title, genre, averageRating, authorId);
    }

    public String delete(Long id) {
        return bookRepository.delete(id);
    }

    public List<Book> searchBooks(String text, Double rating) {
        return this.bookRepository.searchBooks(text, rating);
    }
}
