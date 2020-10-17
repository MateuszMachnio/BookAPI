package pl.bookAPI.service;

import org.springframework.stereotype.Service;
import pl.bookAPI.book.Book;
import pl.bookAPI.book.BookService;

import java.util.*;

@Service
public class MemoryBookService implements BookService {

    private List<Book> books;
    private static Long nextId = 4L;


    public MemoryBookService() {
        books = new ArrayList<>();
        books.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion", "programming"));
        books.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion", "programming"));
    }

    public void addBook(Book book) {
        book.setId(nextId++);
        Objects.requireNonNull(book);
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public Optional<Book> getBook(Long bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst();
    }

    public void editBook(Book editedBook) {
        books.stream()
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

    public boolean deleteBook(Long bookId) {
        if (getBook(bookId).isPresent()) {
            books.remove(getBook(bookId).get());
            return true;
        }
        return false;
    }

//    public Long getNextId() {
//        OptionalLong max = books.stream()
//                .mapToLong(Book::getId)
//                .max();
//        if (max.isPresent()) {
//            return max.getAsLong() + 1;
//        }
//        return 1L;
//    }

}
