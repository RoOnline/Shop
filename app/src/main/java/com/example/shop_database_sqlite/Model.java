package com.example.shop_database_sqlite;

public class Model {

    String id;
    String image;
    String name;
    String phone;

    public Model(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
