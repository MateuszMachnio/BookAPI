package pl.bookAPI.book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBooks();

    boolean addBook(Book book);

    Optional<Book> getBook(Long bookId);

    boolean updateBook(Book book);

    boolean deleteBook(Long bookId);
}
