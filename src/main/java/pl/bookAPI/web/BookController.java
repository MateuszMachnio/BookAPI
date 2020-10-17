package pl.bookAPI.web;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.bookAPI.book.Book;
import pl.bookAPI.book.BookService;
import pl.bookAPI.service.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

//    @PutMapping("/{id}")
//    @PutMapping(path = "", consumes = "application/json")
//    public Book editBook(@PathVariable Long id, @RequestParam(required = false) String isbn, @RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) String publisher, @RequestParam(required = false) String type) {
//    public Book editBook(@PathVariable Long id, @RequestBody Book book) {
//    public void editBook(@RequestBody Book book) {
//        book.setId(id);
//        Book book = memoryBookService.getBook(id);
//        Objects.requireNonNull(book);
//        book.setIsbn(Optional.ofNullable(isbn).orElse(book.getIsbn()));
//        book.setTitle(Optional.ofNullable(title).orElse(book.getTitle()));
//        book.setAuthor(Optional.ofNullable(author).orElse(book.getAuthor()));
//        book.setPublisher(Optional.ofNullable(publisher).orElse(book.getPublisher()));
//        book.setType(Optional.ofNullable(type).orElse(book.getType()));
//        bookService.editBook(book);
//    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getBook(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found");
        } );
    }
//    curl -X PUT -i -H "Content-Type: application/json" -d '{"id":1, "isbn":"32222", "title":"Thinking in C#", "publisher":"IT Books", "type":"programming", "author":"Bruce Eckel"}' http://localhost:8080/books
//    curl -X POST -i -H "Content-Type: application/json" -d '{"isbn":"34321", "title":"Thinking in Java", "publisher":"Helion", "type":"programming", "author":"Bruce Eckel"}' http://localhost:8080/books
    @GetMapping("")
    public List<Book> displayBooks() {
        return bookService.getBooks();
    }

    @PostMapping("")
    public void addBookToList(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
//        bookService.deleteBook(id);
        if (!bookService.deleteBook(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found");
        }
    }

//    @PostMapping("")
//    public String addBookToList(@RequestParam String isbn, @RequestParam String title, @RequestParam String author, @RequestParam String publisher, @RequestParam String type) {
//        bookService.addBook(new Book(isbn,title,author,publisher,type));
//        return "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>What to do</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<h3>What do you wanna do now?</h3>\n" +
//                "<a href=\"/books/addBook\"><button type=\"button\" style=\"margin-right: 40px\">Add next book</button></a>\n" +
//                "<a href=\"/books\"><button type=\"button\">View books</button></a>\n" +
//                "</body>\n" +
//                "</html>";
//    }

//    @GetMapping("/addBook")
//    public String addBook() {
//        return "<!DOCTYPE html>\n" +
//                "<html lang=\"en\">\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <title>Add Book</title>\n" +
//                "</head>\n" +
//                "<body>\n" +
//                "<form action=\"/books\" method=\"post\">\n" +
//                "    <h3>Adding book to list</h3>\n" +
//                "    <p>\n" +
//                "        <input type=\"text\" name=\"isbn\" placeholder=\"ISBN\">\n" +
//                "        <input type=\"text\" name=\"title\" placeholder=\"Book title\">\n" +
//                "        <input type=\"text\" name=\"author\" placeholder=\"Author\">\n" +
//                "        <input type=\"text\" name=\"publisher\" placeholder=\"Publisher\">\n" +
//                "        <input type=\"text\" name=\"type\" placeholder=\"Book type\">\n" +
//                "    </p>\n" +
//                "    <button type=\"submit\">Add book</button>\n" +
//                "</form>\n" +
//                "</body>\n" +
//                "</html>";
//    }

}

