package services;

import models.Book;
import models.Customer;
import models.Purchase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseServiceTest {
    private PurchaseService purchaseService;
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

        CustomerService customerService = new CustomerService();
        try {
            customers = new ArrayList<>();
            customers.add(new Customer("Mark", "Waer", simpleDateFormat.parse("2015-03-12")));
            customers.add(new Customer("Peter", "Santos", simpleDateFormat.parse("2020-10-29")));
            customers.add(new Customer("Nicolai", "Reedtz", simpleDateFormat.parse("2018-07-02")));
            customers.stream().forEach(customer -> customerService.saveCustomer(customer));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUpBeforeEach() {
        purchaseService = new PurchaseService();
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
    void findPurchase() {
        Purchase expectedPurchase = purchases.get(0);
        Purchase actualPurchase = purchaseService.findPurchase(expectedPurchase.getPurchaseId());
        assertEquals(expectedPurchase, actualPurchase);
    }

    @Test
    void savePurchase() {
        try {
            Purchase expectedPurchase = new Purchase(customers.get(0), books.get(0), simpleDateTimeFormat.parse("2021-04-06 11:55:41"));
            purchaseService.savePurchase(expectedPurchase);
            Purchase actualPurchase = purchaseService.findPurchase(expectedPurchase.getPurchaseId());
            assertEquals(expectedPurchase, actualPurchase);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deletePurchase() {
        Purchase purchase = purchases.get(0);
        purchaseService.deletePurchase(purchase);
        Purchase nonExistentPurchase = purchaseService.findPurchase(purchase.getPurchaseId());
        assertNull(nonExistentPurchase);
    }

    @Test
    void updatePurchase() {
        Purchase expectedPurchase = purchases.get(0);
        expectedPurchase.setBook(books.get(0));
        expectedPurchase.setCustomer(customers.get(0));
        purchaseService.updatePurchase(expectedPurchase);
        Purchase actualPurchase = purchaseService.findPurchase(expectedPurchase.getPurchaseId());
        assertEquals(expectedPurchase, actualPurchase);
    }

    @Test
    void findAllPurchases() {
        List<Purchase> actualPurchases = purchaseService.findAllPurchases();
        assertTrue(purchases.containsAll(actualPurchases) && actualPurchases.containsAll(purchases));
    }

    @Test
    void findAllPurchasesByCustomer() {
        List<Purchase> expectedPurchases = purchases
                .stream()
                .filter(purchase -> purchase.getCustomer().getCustomerId() == customers.get(1).getCustomerId())
                .collect(Collectors.toList());
        List<Purchase> actualPurchases = purchaseService.findAllPurchasesByCustomer(customers.get(1));
    }
}