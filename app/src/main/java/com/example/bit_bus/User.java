package com.example.bit_bus;

public class User {

    public String fullName;
    public String roll;
    public String branch;
    public String password;
    public String email;

    public User() {
    }

    public User(String fullName, String roll, String branch, String password, String email) {
        this.fullName = fullName;
        this.roll = roll;
        this.branch = branch;
        this.password = password;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
