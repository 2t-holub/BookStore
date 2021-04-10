package services;

import dao.BookDao;
import dao.BookDaoImpl;
import models.Book;
import models.Customer;
import models.Purchase;

import java.util.List;

public class BookService {

    private BookDao booksDao = new BookDaoImpl();

    public BookService() {
    }

    public Book findBook(long id) {
        return booksDao.findById(id);
    }

    public void saveBook(Book book) {
        booksDao.save(book);
    }

    public void deleteBook(Book book) {
        booksDao.delete(book);
    }

    public void updateBook(Book book) {
        booksDao.update(book);
    }

    public List<Book> findAllBooks() {
        return booksDao.findAll();
    }

}
