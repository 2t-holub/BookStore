package dao;

import models.Customer;
import models.Purchase;

import java.util.List;

public interface PurchaseDao {

    public Purchase findById(long id);

    public void save(Purchase purchase);

    public void update(Purchase purchase);

    public void delete(Purchase purchase);

    public List<Purchase> findAll();

    public List<Purchase> findAllByCustomer(Customer customer);

}
