package com.company;

/**
 * Created by Rajvi on 11/20/17.
 */
public class Bar {
    String name, address, city, state, phone;
    float rating;
    int floors;
    char inspection;

    public Bar(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.address;
    }

    public String getState() {
        return this.state;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getFloors() {
        return this.floors;
    }

    public float getRating() {
        return this.rating;
    }

    public char getInspection() {
        return this.inspection;
    }

}
