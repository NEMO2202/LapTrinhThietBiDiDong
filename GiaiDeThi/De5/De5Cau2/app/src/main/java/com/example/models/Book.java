package com.example.models;

import java.io.Serializable;

public class Book implements Serializable {
    String BookId;
    String BookName;
    Double BookPrice;

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public Double getBookPrice() {
        return BookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        BookPrice = bookPrice;
    }

    public Book(String bookId, String bookName, Double bookPrice) {
        BookId = bookId;
        BookName = bookName;
        BookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "Mã sách: " + BookId+ '\n' + "Tên sách " + BookName + '\n' + "Giá bán: " + BookPrice ;

    }
}
