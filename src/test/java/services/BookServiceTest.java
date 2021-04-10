package services;

import models.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;
    private List<Book> books;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        books = bookService.findAllBooks();
        books.stream().forEach(book -> bookService.deleteBook(book));
        books.clear();
        books.add(new Book("DNK", 237, "Fozzi", 134.5));
        books.add(new Book("Dog trip", 231, "Brus Kemeron", 151.9));
        books.add(new Book("Your way", 191, "Olena Rezanova", 230));
        books.add(new Book("Sweer formula of success", 112, "Ellen Singer", 92.8));
        books.stream().forEach(book -> bookService.saveBook(book));
    }

    @Test
    void findBook() {
        Book expectedBook = books.get(0);
        Book actualBook = bookService.findBook(expectedBook.getBook_id());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void saveBook() {
        Book expectedBook = new Book("DNK2", 194, "Karpa", 121.98);
        bookService.saveBook(expectedBook);
        Book actualBook = bookService.findBook(expectedBook.getBook_id());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void deleteBook() {
        Book book = books.get(0);
        bookService.deleteBook(book);
        Book nonExistentBook = bookService.findBook(book.getBook_id());
        assertNull(nonExistentBook);
    }

    @Test
    void updateBook() {
        Book expectedBook = books.get(0);
        expectedBook.setBook_name("DNK (part1)");
        expectedBook.setBook_price(150.99);
        bookService.updateBook(expectedBook);
        Book actualBook = bookService.findBook(expectedBook.getBook_id());
        assertEquals(expectedBook, actualBook);
    }

    @Test
    void findAllBooks() {
        List<Book> actualBooks = bookService.findAllBooks();
        assertTrue(books.containsAll(actualBooks) && actualBooks.containsAll(books));
    }
}