package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String customer_name;
    private String customer_surname;
    @Temporal(TemporalType.TIMESTAMP)
    private Date customer_register_date;

    public Customer() {
    }

    public Customer(String customer_name, String customer_surname, Date customer_register_date) {
        this.customer_name = customer_name;
        this.customer_surname = customer_surname;
        this.customer_register_date = customer_register_date;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_surname() {
        return customer_surname;
    }

    public void setCustomer_surname(String customer_surname) {
        this.customer_surname = customer_surname;
    }

    public Date getCustomer_register_date() {
        return customer_register_date;
    }

    public void setCustomer_register_date(Date customer_register_date) {
        this.customer_register_date = customer_register_date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_name='" + customer_name + '\'' +
                ", customer_surname='" + customer_surname + '\'' +
                ", customer_register_date=" + customer_register_date +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Customer other = (Customer) obj;
        if ((this.customer_name == null) ? (other.customer_name != null) : !this.customer_name.equals(other.customer_name)) {
            return false;
        }

        if ((this.customer_surname == null) ? (other.customer_surname != null) : !this.customer_surname.equals(other.customer_surname)) {
            return false;
        }

        if ((this.customer_register_date == null) ? (other.customer_register_date != null) : this.customer_register_date.getTime()!=other.customer_register_date.getTime()) {
            System.out.println(this.customer_register_date.getTime()+" _ "+other.customer_register_date.getTime());
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.customer_name != null ? this.customer_name.hashCode() : 0);
        hash = 53 * hash + (this.customer_surname != null ? this.customer_surname.hashCode() : 0);
        hash = 53 * hash + (this.customer_register_date != null ? this.customer_register_date.hashCode() : 0);
        hash = 53 * hash + (this.customer_id != null ? this.customer_id.hashCode() : 0);
        return hash;
    }
}
