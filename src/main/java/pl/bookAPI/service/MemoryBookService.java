package pl.bookAPI.service;

import org.springframework.stereotype.Service;
import pl.bookAPI.book.Book;

import java.util.*;

@Service
public class MemoryBookService {

    private List<Book> books;
//    private static Long nextId = 4L;


    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion", "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion", "programming"));
    }

    public void addBook(Book book) {
        Objects.requireNonNull(book);
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Book getBook(Long bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst().orElse(null);
    }

    public Book editBook(Book editedBook) {
            return books.stream()
                    .filter(book -> book.getId().equals(editedBook.getId()))
                    .limit(1)
                    .peek(book -> {
                        book.setIsbn(editedBook.getIsbn());
                        book.setTitle(editedBook.getTitle());
                        book.setAuthor(editedBook.getAuthor());
                        book.setPublisher(editedBook.getPublisher());
                        book.setType(editedBook.getType());
                    })
                    .findFirst().orElse(null);
    }

    public void deleteBook(Long bookId) {
        books.stream()
                .filter(book -> book.getId().equals(bookId))
                .limit(1)
                .forEach(book -> books.remove(book));
    }

    public Long getNextId() {
        OptionalLong max = books.stream()
                .mapToLong(Book::getId)
                .max();
        if (max.isPresent()) {
            return max.getAsLong() + 1;
        }
        return 1L;
    }

}
