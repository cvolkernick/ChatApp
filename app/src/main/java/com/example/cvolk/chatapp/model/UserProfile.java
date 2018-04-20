package com.example.cvolk.chatapp.model;

public class UserProfile {

    private String firstName;
    private String lastName;
    private String location;
    private String age;

    public UserProfile(String firstName, String lastName, String location, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
