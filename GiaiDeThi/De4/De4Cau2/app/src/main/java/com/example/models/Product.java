package com.example.models;

import java.io.Serializable;

public class Product implements Serializable {
     String ProductId;
     String ProductName;
     Float ProductPrice;

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

    public Float getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Float productPrice) {
        ProductPrice = productPrice;
    }

    public Product(String productId, String productName, Float productPrice) {
        this.ProductId = productId;
        this.ProductName = productName;
        this.ProductPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Mã sản phẩm: " + ProductId + '\n' + "Tên sản phẩm: " + ProductName + '\n' + "Giá sản phẩm: " + ProductPrice ;

    }
}
