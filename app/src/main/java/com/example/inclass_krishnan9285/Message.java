package com.example.inclass_krishnan9285;

import java.io.Serializable;

public class Message implements Serializable {
    private String email;
    private String message;


    public Message(String email, String message) {
        this.email = email;
        this.message = message;

    }

    public Message(String message) {
        this.message = message;

    }

    public Message(){
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUser() {
        return email;
    }
    public void setUser(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
