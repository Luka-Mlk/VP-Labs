package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> searchBooks(String text, Double rating);
    Book getById(Long id);
    Book create(String title, String genre, double averageRating, Long authorId);
    Book update(Long id, String title, String genre, Double averageRating, Long authorId);
    String delete(Long id);
}
