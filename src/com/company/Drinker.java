package com.company;

/**
 * Created by Rajvi on 11/21/17.
 */
public class Drinker {
    int id, age, crimes, tattoos, friends_entered, friends_left;
    float original_gpa, current_gpa, duration, time_entered, bac;
    String first_name, last_name, phone, gender, major, relationship, returned;

    public Drinker(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public int getAge() {
        return this.age;
    }

    public float getOriginal_gpa() {
        return this.original_gpa;
    }

    public float getCurrent_gpa() {
        return this.current_gpa;
    }

    public int getCrimes() {
        return this.crimes;
    }

    public int getTattoos() {
        return this.tattoos;
    }

    public int getFriends_entered() {
        return this.friends_entered;
    }

    public float getDuration() {
        return this.duration;
    }

    public float getTime_entered() {
        return this.time_entered;
    }

    public float getBac() {
        return this.bac;
    }

    public int getFriends_left() {
        return this.friends_left;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getGender() {
        return this.gender;
    }

    public String getMajor() {
        return this.major;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String getReturned() {
        return this.returned;
    }
}
