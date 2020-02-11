package com.my.kissandost;

public class ProductDetails {

    public String ProductCategory;
    public String ProductName;
    public String ProdQuantity;
    public String ProductBasePrice;

    public ProductDetails() {

    }

    public ProductDetails(String productCategory, String productName, String prodQuantity, String productBasePrice) {
        this.ProductCategory = productCategory;
        this.ProductName = productName;
        this.ProdQuantity = prodQuantity;
        this.ProductBasePrice = productBasePrice;
    }

}
