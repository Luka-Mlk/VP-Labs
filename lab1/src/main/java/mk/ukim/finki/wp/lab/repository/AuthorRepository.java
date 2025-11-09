package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> findAll();
    List<Author> findById(Long id);
}
