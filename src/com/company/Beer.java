package com.company;

/**
 * Created by Rajvi on 11/19/17.
 */
public class Beer {
    String brand;
    int Calories;
    float ABV, Efficiency, Rating;

    public Beer(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }

    public int getCalories() {
        return this.Calories;
    }

    public float getABV() {
        return this.ABV;
    }

    public float getEfficiency() {
        return this.Efficiency;
    }

    public float getRating() {
        return this.Rating;
    }

}
