package models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_surname")
    private String customerSurname;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "customer_register_date")
    private Date customerRegisterDate;

    public Customer() {
    }

    public Customer(String customerName, String customerSurname, Date customerRegisterDate) {
        this.customerName = customerName;
        this.customerSurname = customerSurname;
        this.customerRegisterDate = customerRegisterDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public Date getCustomerRegisterDate() {
        return customerRegisterDate;
    }

    public void setCustomerRegisterDate(Date customerRegisterDate) {
        this.customerRegisterDate = customerRegisterDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", customerRegisterDate=" + customerRegisterDate +
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
        if ((this.customerName == null) ? (other.customerName != null) : !this.customerName.equals(other.customerName)) {
            return false;
        }

        if ((this.customerSurname == null) ? (other.customerSurname != null) : !this.customerSurname.equals(other.customerSurname)) {
            return false;
        }

        if ((this.customerRegisterDate == null) ? (other.customerRegisterDate != null) : this.customerRegisterDate.getTime()!=other.customerRegisterDate.getTime()) {
            System.out.println(this.customerRegisterDate.getTime()+" _ "+other.customerRegisterDate.getTime());
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.customerName != null ? this.customerName.hashCode() : 0);
        hash = 53 * hash + (this.customerSurname != null ? this.customerSurname.hashCode() : 0);
        hash = 53 * hash + (this.customerRegisterDate != null ? this.customerRegisterDate.hashCode() : 0);
        hash = 53 * hash + (this.customerId != null ? this.customerId.hashCode() : 0);
        return hash;
    }
}
