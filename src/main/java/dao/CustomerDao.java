package dao;

import models.Customer;

import java.util.List;

public interface CustomerDao {

    public Customer findById(long id);

    public void save(Customer customer);

    public void update(Customer customer);

    public void delete(Customer customer);

    public List<Customer> findAll();

}
