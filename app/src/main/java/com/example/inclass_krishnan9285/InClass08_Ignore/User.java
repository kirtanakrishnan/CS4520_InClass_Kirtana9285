package com.example.inclass_krishnan9285.InClass08_Ignore;

import java.io.Serializable;

public class User implements Serializable {
    String fname;
    String lname;
    String displayname;
    String email;

    public User(String fname, String lname, String displayname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.displayname = displayname;
        this.email = email;
    }

    public User() {}

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", displayname='" + displayname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
