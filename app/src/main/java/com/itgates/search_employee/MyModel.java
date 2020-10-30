package com.itgates.search_employee;

class MyModel {
    String Category;
    String place;
    String description;
    String Uid;

    public MyModel(String category, String place, String description, String uid) {
        Category = category;
        this.place = place;
        this.description = description;
        Uid = uid;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
