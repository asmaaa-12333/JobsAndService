package com.itgates.search_employee;

public class Model_rent {
    String image;
    String productName;
    String description;
    String price;
    String personName ;
    String personPhone;


    public Model_rent(String image, String productName, String description, String price, String personName, String personPhone) {
        this.image = image;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.personName = personName;
        this.personPhone = personPhone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }
}
