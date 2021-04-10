package services;

import dao.CustomerDao;
import dao.CustomerDaoImpl;
import models.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDao customersDao = new CustomerDaoImpl();

    public CustomerService() {
    }

    public Customer findCustomer(long id) {
        return customersDao.findById(id);
    }

    public void saveCustomer(Customer customer) {
        customersDao.save(customer);
    }

    public void deleteCustomer(Customer customer) {
        customersDao.delete(customer);
    }

    public void updateCustomer(Customer customer) {
        customersDao.update(customer);
    }

    public List<Customer> findAllCustomers() {
        return customersDao.findAll();
    }

}
