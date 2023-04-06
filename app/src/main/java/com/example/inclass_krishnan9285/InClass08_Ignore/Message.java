package com.example.inclass_krishnan9285.InClass08_Ignore;

import java.io.Serializable;

public class Message implements Serializable {
    private String displayName;
    private String message;

    public Message(String displayName, String message) {
        this.displayName = displayName;
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
        return displayName;
    }
    public void setUser(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", email='" + displayName + '\'' +
                '}';
    }
}
