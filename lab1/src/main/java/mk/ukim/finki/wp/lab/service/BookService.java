package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listAll();
    Book getById(Long id);
    Book create(String title, String genre, double averageRating, Long authorId);
    List<Book> searchBooks(String text, Double rating);
    Book update(Long id, String title, String genre, Double averageRating, Long authorId);
    String delete(Long id);
}
