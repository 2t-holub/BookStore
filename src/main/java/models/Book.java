package models;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {     //POJO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;
    private String book_name;
    private int page_number;
    private String book_author;
    private double book_price;

    public Book() {
    }

    public Book(String book_name, int page_number, String book_author, double book_price) {
        this.book_name = book_name;
        this.page_number = page_number;
        this.book_author = book_author;
        this.book_price = book_price;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public double getBook_price() {
        return book_price;
    }

    public void setBook_price(double book_price) {
        this.book_price = book_price;
    }

    @Override
    public String toString() {
        return book_id + ". \""
                + book_name + "\" ("
                + book_author + ") "
                + page_number + " pages - "
                + book_price + "₴";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Book other = (Book) obj;
        if ((this.book_name == null) ? (other.book_name != null) : !this.book_name.equals(other.book_name)) {
            return false;
        }

        if ((this.book_author == null) ? (other.book_author != null) : !this.book_author.equals(other.book_author)) {
            return false;
        }

        if ((this.book_id == null) ? (other.book_id != null) : !this.book_id.equals(other.book_id)) {
            return false;
        }

        if (this.page_number != other.page_number) {
            return false;
        }


        if ((int)((this.book_price+0.005)*100) != (int)((other.book_price+0.005)*100)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.book_name != null ? this.book_name.hashCode() : 0);
        hash = 53 * hash + (this.book_author != null ? this.book_author.hashCode() : 0);
        hash = 53 * hash + (this.book_id != null ? this.book_id.hashCode() : 0);
        hash = 53 * hash + this.page_number;
        hash = 53 * hash + (int)(this.book_price+0.5);
        return hash;
    }
}
