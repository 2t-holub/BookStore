/*
My project for learning Hibernate
 */

import models.Book;
import models.Customer;
import models.Purchase;
import services.BookService;
import services.CustomerService;
import services.PurchaseService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static BookService bookService = new BookService();
    private static CustomerService customerService = new CustomerService();
    private static PurchaseService purchaseService = new PurchaseService();

    public static void main(String[] args) {

        //bookCrudTest();
        //customerCrudTest();
        //purchaseCrudTest();

        try {
            Customer customer = meetCustomer();
            Book book = offerBooks();
            makePurchase(customer, book);
            showFullCustomerInformation(customer);
            System.out.println("See you later!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Customer meetCustomer() throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello, glad to see you in our bookstore! To make a purchase - log in.");
        System.out.print("Enter your customer id. If you don`t have it enter 0: ");
        long enteredCustomerId = Long.valueOf(bufReader.readLine());
        Customer customer = customerService.findCustomer(enteredCustomerId);
        if (Objects.isNull(customer)) {
            customer = registerNewCustomer();
            System.out.println("write register function here");
        }
        System.out.println("Hello, " + customer.getCustomer_name());
        return customer;
    }

    public static Customer registerNewCustomer() throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter your name and surname, please: ");
        String fullName = bufReader.readLine();
        String name = "";
        String surname = "";
        if(fullName.contains(" ")) {
            name = fullName.substring(0, fullName.indexOf(" "));
            surname = fullName.substring(fullName.indexOf(" ") + 1);
        }
        else{
            name = fullName;
        }
        Customer customer = new Customer(name, surname, new Date());
        customerService.saveCustomer(customer);
        System.out.println(customer);
        return customer;
    }

    public static Book offerBooks() throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
        Book chosenBook = new Book();
        List<Book> books = bookService.findAllBooks();
        if (books.size() == 0) {
            System.out.println("Oops, our store was empty, so I will sell my own book.");
            Book book1 = new Book("DNK", 137, "Karpa", 12.98);
            bookService.saveBook(book1);
            books = bookService.findAllBooks();
        } else {
            System.out.println("Quarantine has hit our business hard, so we can't let you go without a purchase.\n"
                    + "What book do you want to buy? Here is a complete list of available books:");
        }
        books
                .stream()
                .forEach(book -> System.out.println(book));
        while (true) {
            System.out.print("\nEnter the number of chosen book: ");
            long enteresBookId = Long.valueOf(bufReader.readLine());
            chosenBook = bookService.findBook(enteresBookId);
            if (!Objects.isNull(chosenBook)) {
                return chosenBook;
            }
        }
    }

    public static boolean makePurchase(Customer customer, Book book) {
        System.out.println("You choose book: " + book);
        //I imagine that there is a payment here. Function will return false, if some problem arise.
        Purchase purchase = new Purchase(customer, book, new Date());
        purchaseService.savePurchase(purchase);
        System.out.println("Thanks for your purchase! \"" + book.getBook_name() + "\" (" + book.getBook_author() + ") is yours.");
        return true;
    }

    public static void showFullCustomerInformation(Customer customer) {
        List<Purchase> purchasesOfCustomer = purchaseService.findAllPurchasesByCustomer(customer);
        System.out.println("\nYour purchases, " + customer.getCustomer_name() + " " + customer.getCustomer_surname() + ": ");
        purchasesOfCustomer.stream().forEach(purchase -> System.out.println(purchase.getStrBookInfo()));
    }
}
