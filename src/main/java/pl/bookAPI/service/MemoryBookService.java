package pl.bookAPI.service;

import org.springframework.stereotype.Service;
import pl.bookAPI.book.Book;

import java.util.*;

@Service
public class MemoryBookService {

    private List<Book> books;

    public MemoryBookService() {
        books = new ArrayList<>();
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

    public boolean editBook(Book editedBook) {
        boolean anyMatch = books.stream()
                .anyMatch(book -> book.getId().equals(editedBook.getId()));
        if (anyMatch) {
            Optional<Book> thisBook = books.stream()
                    .filter(book -> book.getId().equals(editedBook.getId()))
                    .findFirst();
            thisBook.ifPresent(book -> {
                book.setIsbn(editedBook.getIsbn());
                book.setTitle(editedBook.getTitle());
                book.setAuthor(editedBook.getAuthor());
                book.setPublisher(editedBook.getPublisher());
                book.setType(editedBook.getType());
            });
        }
        return anyMatch;
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
