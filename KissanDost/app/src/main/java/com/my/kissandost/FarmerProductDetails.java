package com.my.kissandost;

public class FarmerProductDetails {


    private String productId,pName,farmerId,farmerName,bPrice,quantity,productCategory;

    FarmerProductDetails(String productIdID,String productName,String farmerid, String farmerName,String basePrice,String qty,String prodCategory){
        this.productId = productIdID;
        this.pName = productName;
        this.farmerId = farmerid;
        this.farmerName = farmerName;
        this.bPrice = basePrice;
        this.quantity = qty;
        this.productCategory = prodCategory;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public String getbPrice() {
        return bPrice;
    }

    public void setbPrice(String bPrice) {
        this.bPrice = bPrice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPcategory() {
        return productCategory;
    }

    public void setPcategory(String pcategory) {
        this.productCategory = pcategory;
    }



}
