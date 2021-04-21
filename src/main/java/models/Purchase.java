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
    @Column(name = "purchase_id")
    private Long purchaseId;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    @ManyToOne()
    @JoinColumn(name = "book_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Book book;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_datetime")
    private Date purchaseDatetime;

    public Purchase() {
    }

    public Purchase(Customer customer, Book book, Date purchaseDatetime) {
        this.customer = customer;
        this.book = book;
        this.purchaseDatetime = purchaseDatetime;
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

    public Long getPurchaseId() {
        return purchaseId;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchase_id=" + purchaseId +
                ", customer=" + customer +
                ", book=" + book +
                ", purchase_datetime=" + purchaseDatetime +
                '}';
    }

    public String getStrBookInfo() {
        return book + " {" + purchaseDatetime + "} ";
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

        if ((this.purchaseDatetime == null) ? (other.purchaseDatetime != null) : (this.purchaseDatetime.getTime()/1000)!=(other.purchaseDatetime.getTime()/1000)) {
            System.out.println(this.purchaseDatetime.getTime()+" _ "+other.purchaseDatetime.getTime());
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 53 * hash + (this.book != null ? this.book.hashCode() : 0);
        hash = 53 * hash + (this.purchaseDatetime != null ? this.purchaseDatetime.hashCode() : 0);
        hash = 53 * hash + (this.purchaseId != null ? this.purchaseId.hashCode() : 0);
        return hash;
    }
}
