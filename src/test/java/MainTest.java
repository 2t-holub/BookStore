import models.Book;
import models.Customer;
import models.Purchase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BookService;
import services.CustomerService;
import services.PurchaseService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private CustomerService customerService = new CustomerService();
    private PurchaseService purchaseService = new PurchaseService();
    private static List<Book> books;
    private static List<Customer> customers;
    private static List<Purchase> purchases;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    static void setUp() {
        BookService bookService = new BookService();
        books = new ArrayList<>();
        books.add(new Book("DNK", 237, "Fozzi", 134.5));
        books.add(new Book("Dog trip", 231, "Brus Kemeron", 151.9));
        books.add(new Book("Your way", 191, "Olena Rezanova", 230));
        books.add(new Book("Sweer formula of success", 112, "Ellen Singer", 92.8));
        books.stream().forEach(book -> bookService.saveBook(book));
    }

    @BeforeEach
    void setUpBeforeEach() {
        customers = customerService.findAllCustomers();
        customers.stream().forEach(customer -> customerService.deleteCustomer(customer));
        customers.clear();
        try {
            customers = new ArrayList<>();
            customers.add(new Customer("Mark", "Waer", simpleDateFormat.parse("2015-03-12")));
            customers.add(new Customer("Peter", "Santos", simpleDateFormat.parse("2020-10-29")));
            customers.add(new Customer("Nicolai", "Reedtz", simpleDateFormat.parse("2018-07-02")));
            customers.stream().forEach(customer -> customerService.saveCustomer(customer));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        purchases = purchaseService.findAllPurchases();
        purchases.stream().forEach(purchase -> purchaseService.deletePurchase(purchase));
        purchases.clear();
        try {
            purchases.add(new Purchase(customers.get(0), books.get(2), simpleDateTimeFormat.parse("2021-02-12 14:28:03")));
            purchases.add(new Purchase(customers.get(1), books.get(1), simpleDateTimeFormat.parse("2021-01-19 10:51:58")));
            purchases.add(new Purchase(customers.get(1), books.get(0), simpleDateTimeFormat.parse("2021-02-27 21:06:47")));
            purchases.add(new Purchase(customers.get(2), books.get(1), simpleDateTimeFormat.parse("2021-03-02 04:13:28")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        purchases.stream().forEach(purchase -> purchaseService.savePurchase(purchase));
    }

    @Test
    void meetCustomer() throws IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(customers.get(1).getCustomer_id()).getBytes());
        System.setIn(in);
        Customer actualCustomer =  Main.meetCustomer();
        Customer expectedCustomer = customers.get(1);
        assertEquals(expectedCustomer, actualCustomer);
        System.setIn(sysInBackup);
    }

    @Test
    void registerNewCustomer() throws IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("Name Surname".getBytes());
        System.setIn(in);
        Customer actualCustomer =  Main.registerNewCustomer();
        assertEquals("Name", actualCustomer.getCustomer_name());
        assertEquals("Surname", actualCustomer.getCustomer_surname());
        assertTrue(!customers.contains(actualCustomer));
        System.setIn(sysInBackup);
    }

    @Test
    void offerBooks() throws IOException {
        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream(String.valueOf(books.get(0).getBook_id()).getBytes());
        System.setIn(in);
        Book actualBook =  Main.offerBooks();
        Book expectedBook = books.get(0);
        assertEquals(expectedBook, actualBook);
        System.setIn(sysInBackup);
    }

    @Test
    void makePurchase() {
        Customer customer = customers.get(0);
        Book book = books.get(0);
        List<Purchase> customerPurchaseListBefore = purchaseService.findAllPurchasesByCustomer(customer);
        long numberOfPurchasesBefore = customerPurchaseListBefore
                .stream()
                .filter(purchase -> purchase.getBook().equals(book))
                .count();
        Main.makePurchase(customer, book);
        List<Purchase> customerPurchaseListAfter = purchaseService.findAllPurchasesByCustomer(customer);
        long numberOfPurchasesAfter = customerPurchaseListAfter
                .stream()
                .filter(purchase -> purchase.getBook().equals(book))
                .count();
        assertEquals(numberOfPurchasesBefore+1, numberOfPurchasesAfter);
    }
}