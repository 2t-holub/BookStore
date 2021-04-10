package services;

import models.Book;
import models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerService customerService;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
        customers = customerService.findAllCustomers();
        customers.stream().forEach(customer -> customerService.deleteCustomer(customer));
        customers.clear();
        try {
            customers.add(new Customer("Mark", "Waer", new SimpleDateFormat("yyyy-MM-dd").parse("2015-03-12")));
            customers.add(new Customer("Peter", "Santos", new SimpleDateFormat("yyyy-MM-dd").parse("2020-10-29")));
            customers.add(new Customer("Nicolai", "Reedtz", new SimpleDateFormat("yyyy-MM-dd").parse("2018-07-02")));
            customers.stream().forEach(customer -> customerService.saveCustomer(customer));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findCustomer() {
        Customer expectedCustomer = customers.get(0);
        Customer actualCustomer = customerService.findCustomer(expectedCustomer.getCustomer_id());
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void saveCustomer() {
        try {
            Customer expectedCustomer = new Customer("Lukas", "Johnson", new SimpleDateFormat("yyyy-MM-dd").parse("2021-02-09"));
            customerService.saveCustomer(expectedCustomer);
            Customer actualCustomer = customerService.findCustomer(expectedCustomer.getCustomer_id());
            assertEquals(expectedCustomer, actualCustomer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteCustomer() {
        Customer customer = customers.get(0);
        customerService.deleteCustomer(customer);
        Customer nonExistentCustomer = customerService.findCustomer(customer.getCustomer_id());
        assertNull(nonExistentCustomer);
    }

    @Test
    void updateCustomer() {
        Customer expectedCustomer = customers.get(0);
        expectedCustomer.setCustomer_name("Oleg");
        expectedCustomer.setCustomer_surname("Teslenko");
        customerService.updateCustomer(expectedCustomer);
        Customer actualCustomer = customerService.findCustomer(expectedCustomer.getCustomer_id());
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void findAllCustomers() {
        List<Customer> actualCustomers = customerService.findAllCustomers();
        assertTrue(customers.containsAll(actualCustomers) && actualCustomers.containsAll(customers));
    }
}