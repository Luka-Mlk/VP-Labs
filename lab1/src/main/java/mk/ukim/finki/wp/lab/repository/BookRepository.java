package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByTitleContainingAndAverageRatingGreaterThanEqual(String title, Double rating);
    List<Book> findAllByAuthor_Id(Long authorId);
}
