package com.example.models;

import java.io.Serializable;

public class Product implements Serializable {
    String ProductId;
    String ProductName;
    Double ProductPrice;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Double productPrice) {
        ProductPrice = productPrice;
    }

    public Product(String productId, String productName, Double productPrice) {
        ProductId = productId;
        ProductName = productName;
        ProductPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Mã sách: " + ProductId+ '\n' + "Tên sách " + ProductName + '\n' + "Giá bán: " + ProductPrice ;

    }
}
