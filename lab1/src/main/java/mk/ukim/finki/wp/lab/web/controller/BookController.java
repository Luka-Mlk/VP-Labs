package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public String getBooksPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Book> books = bookService.listAll(); // Use the service method that fetches from the DB

        model.addAttribute("books", books);

        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Double averageRating,
            @RequestParam Long authorId
    ){
        bookService.create(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(
            @PathVariable Long bookId,
            @RequestParam String title,
            @RequestParam String genre,
            @RequestParam Double averageRating,
            @RequestParam Long authorId
    ) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/book-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> bookToEdit = bookService.getById(id);

        if (bookToEdit.isEmpty()) {
            return "redirect:/books?error=BookNotFound";
        } else {
            List<Author> authors = authorService.findAll();
            model.addAttribute("book", bookToEdit.get());
            model.addAttribute("authors", authors);
            return "book-form";  // Return the edit form view
        }
    }

    // Display the form for adding a new book
    @GetMapping("/book-form")
    public String getAddBookPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "book-form";  // Return the form for adding a new book
    }

    @GetMapping("/books/{author-id}")
    public List<Book> getBooksByAuthorId(@PathVariable("author-id") Long authorId) {
       return bookService.findAllByAuthor_Id(authorId);

    }
}
