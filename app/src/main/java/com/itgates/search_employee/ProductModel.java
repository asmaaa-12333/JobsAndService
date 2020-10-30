package com.itgates.search_employee;

public class ProductModel {
    private String imgUrl;
    private String title;
    private String price;

    public ProductModel(String imgUrl, String title, String price) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
