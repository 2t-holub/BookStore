package services;

import dao.PurchaseDao;
import dao.PurchaseDaoImpl;
import models.Customer;
import models.Purchase;

import java.util.List;

public class PurchaseService {

    private PurchaseDao purchaseDao = new PurchaseDaoImpl();

    public PurchaseService() {
    }

    public Purchase findPurchase(long id) {
        return purchaseDao.findById(id);
    }

    public void savePurchase(Purchase purchase) {
        purchaseDao.save(purchase);
    }

    public void deletePurchase(Purchase purchase) {
        purchaseDao.delete(purchase);
    }

    public void updatePurchase(Purchase purchase) {
        purchaseDao.update(purchase);
    }

    public List<Purchase> findAllPurchases() {
        return purchaseDao.findAll();
    }

    public List<Purchase> findAllPurchasesByCustomer(Customer customer) {
        return purchaseDao.findAllByCustomer(customer);
    }

}
