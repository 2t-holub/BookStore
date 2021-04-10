package models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchase_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @Temporal(TemporalType.TIMESTAMP)
    private Date purchase_datetime;

    public Purchase() {
    }

    public Purchase(Customer customer, Book book, Date purchase_datetime) {
        this.customer = customer;
        this.book = book;
        this.purchase_datetime = purchase_datetime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getPurchase_datetime() {
        return purchase_datetime;
    }

    public void setPurchase_datetime(Date purchase_datetime) {
        this.purchase_datetime = purchase_datetime;
    }

    public Long getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(Long purchase_id) {
        this.purchase_id = purchase_id;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchase_id=" + purchase_id +
                ", customer=" + customer +
                ", book=" + book +
                ", purchase_datetime=" + purchase_datetime +
                '}';
    }

    public String getStrBookInfo() {
        return book + " {" + purchase_datetime + "} ";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Purchase other = (Purchase) obj;
        if ((this.customer == null) ? (other.customer != null) : !this.customer.equals(other.customer)) {
            return false;
        }

        if ((this.book == null) ? (other.book != null) : !this.book.equals(other.book)) {
            return false;
        }

        if ((this.purchase_datetime == null) ? (other.purchase_datetime != null) : (this.purchase_datetime.getTime()/1000)!=(other.purchase_datetime.getTime()/1000)) {
            System.out.println(this.purchase_datetime.getTime()+" _ "+other.purchase_datetime.getTime());
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 53 * hash + (this.book != null ? this.book.hashCode() : 0);
        hash = 53 * hash + (this.purchase_datetime != null ? this.purchase_datetime.hashCode() : 0);
        hash = 53 * hash + (this.purchase_id != null ? this.purchase_id.hashCode() : 0);
        return hash;
    }
}
