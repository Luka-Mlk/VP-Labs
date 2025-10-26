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

    public List<Book> searchBooks(String text, Double rating) {
        return this.bookRepository.searchBooks(text, rating);
    }
}
