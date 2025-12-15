package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> getById(Long id);
    Book create(String title, String genre, double averageRating, Long authorId);
    List<Book> searchBooks(String text, Double rating);
    Book update(Long id, String title, String genre, Double averageRating, Long authorId);
    String delete(Long id);
    List<Book> findAllByAuthor_Id(Long authorId);
}
