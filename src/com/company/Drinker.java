package com.company;

/**
 * Created by Rajvi on 11/21/17.
 */
public class Drinker {
    int id, age, original_gpa, current_gpa, crimes, tattoos, friends_entered, duration, time_entered, bac, friends_left;
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

    public int getOriginal_gpa() {
        return this.original_gpa;
    }

    public int getCurrent_gpa() {
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

    public int getDuration() {
        return this.duration;
    }

    public int getTime_entered() {
        return this.time_entered;
    }

    public int getBac() {
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
