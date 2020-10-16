package pl.bookAPI.service;

import org.springframework.stereotype.Service;
import pl.bookAPI.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

}
