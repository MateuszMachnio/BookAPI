package pl.bookAPI.web;

import org.springframework.web.bind.annotation.*;
import pl.bookAPI.book.Book;
import pl.bookAPI.service.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private MemoryBookService memoryBookService;

    public BookController(MemoryBookService memoryBookService) {
        this.memoryBookService = memoryBookService;
    }

    @GetMapping("")
    public List<Book> displayBooks() {
        return memoryBookService.getBooks();
    }

    @PostMapping("")
    public String addBookToList() {

        return "";
    }

    @RequestMapping("/addBook")
    public String addBook() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Add Book</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/books\" method=\"post\">\n" +
                "    <h3>Adding book to list</h3>\n" +
                "    <p>\n" +
                "        <input type=\"number\" min=\"0\" step=\"1\" name=\"isbn\" placeholder=\"ISBN\">\n" +
                "        <input type=\"text\" name=\"title\" placeholder=\"Book title\">\n" +
                "        <input type=\"text\" name=\"author\" placeholder=\"Author\">\n" +
                "        <input type=\"text\" name=\"publisher\" placeholder=\"Publisher\">\n" +
                "        <input type=\"text\" name=\"type\" placeholder=\"Book type\">\n" +
                "    </p>\n" +
                "    <button type=\"submit\">Add book</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
    }

}

