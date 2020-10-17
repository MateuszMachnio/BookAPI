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

    @Override
    public boolean addBook(Book book) {
        book.setId(nextId++);
        books.add(book);
        return getBook(nextId - 1).isPresent();
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Optional<Book> getBook(Long bookId) {
        return books.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst();
    }

    @Override
    public boolean updateBook(Book editedBook) {
        if (getBook(editedBook.getId()).isPresent()) {
            books.stream()
                    .filter(book -> book.getId().equals(editedBook.getId()))
                    .forEach(book -> {
                        book.setIsbn(editedBook.getIsbn());
                        book.setTitle(editedBook.getTitle());
                        book.setAuthor(editedBook.getAuthor());
                        book.setPublisher(editedBook.getPublisher());
                        book.setType(editedBook.getType());
                    });
            return true;
        }
//        if (getBook(editedBook.getId()).isPresent()) {
//            int indexOf = books.indexOf(getBook(editedBook.getId()).get());
//            books.set(indexOf, editedBook);
//            return true;
//        }
        return false;
    }

    @Override
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
