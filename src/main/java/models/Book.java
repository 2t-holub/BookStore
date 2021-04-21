package models;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {     //POJO

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "book_author")
    private String bookAuthor;

    @Column(name = "book_price")
    private double bookPrice;

    public Book() {
    }

    public Book(String bookName, int pageNumber, String bookAuthor, double bookPrice) {
        this.bookName = bookName;
        this.pageNumber = pageNumber;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
    }

    public Long getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return bookId + ". \""
                + bookName + "\" ("
                + bookAuthor + ") "
                + pageNumber + " pages - "
                + bookPrice + "₴";
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
        if ((this.bookName == null) ? (other.bookName != null) : !this.bookName.equals(other.bookName)) {
            return false;
        }

        if ((this.bookAuthor == null) ? (other.bookAuthor != null) : !this.bookAuthor.equals(other.bookAuthor)) {
            return false;
        }

        if ((this.bookId == null) ? (other.bookId != null) : !this.bookId.equals(other.bookId)) {
            return false;
        }

        if (this.pageNumber != other.pageNumber) {
            return false;
        }


        if ((int)((this.bookPrice+0.005)*100) != (int)((other.bookPrice+0.005)*100)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.bookName != null ? this.bookName.hashCode() : 0);
        hash = 53 * hash + (this.bookAuthor != null ? this.bookAuthor.hashCode() : 0);
        hash = 53 * hash + (this.bookId != null ? this.bookId.hashCode() : 0);
        hash = 53 * hash + this.pageNumber;
        hash = 53 * hash + (int)(this.bookPrice+0.5);
        return hash;
    }
}
