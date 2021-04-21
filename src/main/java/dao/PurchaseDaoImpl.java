package dao;

import models.Customer;
import models.Purchase;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class PurchaseDaoImpl implements PurchaseDao{

    @Override
    public Purchase findById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Purchase purchase = session.get(Purchase.class, id);
        session.close();
        return purchase;
    }

    @Override
    public void save(Purchase purchase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(purchase);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Purchase purchase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(purchase);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Purchase purchase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(purchase);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Purchase> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Purchase> purchases = session.createQuery("From Purchase").list();
        session.close();
        return purchases;
    }

    @Override
    public List<Purchase> findAllByCustomer(Customer customer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Purchase> purchases = session.createQuery("From Purchase Where customer = " + customer.getCustomerId()).list();
        session.close();
        return purchases;
    }
}
