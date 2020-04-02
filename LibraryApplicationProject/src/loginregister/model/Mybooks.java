/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginregister.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author mertonat
 */
public class Mybooks {
    private SimpleStringProperty bookName;
    private SimpleStringProperty author;
    private SimpleFloatProperty price;
    private SimpleFloatProperty discount;

    public Mybooks(String  bookName, String author, float price, int discount) {
        this.bookName = new SimpleStringProperty(bookName);
        this.author = new SimpleStringProperty(author);
        this.price = new SimpleFloatProperty(price);
        this.discount = new SimpleFloatProperty(discount);
    }

    public Mybooks() {
       
    }

    /**
     * @return the bookName
     */
    public String getBookName() {
        return bookName.get();
    }

    /**
     * @param bookName the bookName to set
     */
    public void setBookName(String bookName) {
      this.bookName.set(bookName);
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author.get();
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author.set(author);
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price.get();
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price.set(price);
    }

    /**
     * @return the discount
     */
    public int getDiscount() {
        return (int) discount.get();
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount.set(discount);
    }

    @Override
    public String toString() {
        return "Books{" + "bookName=" + bookName + ", author=" + author + ", price=" + price + ", discount=" + discount + '}';
    }
    
}
