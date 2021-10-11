package com.example.demo;

import java.util.List;
import java.util.Map;

public class CustomObject {
   Map message;
   String status;

    public Map getMessage() {
        return message;
    }

    public CustomObject() {
    }

    public void setMessage(Map message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomObject(Map message, String status) {
        this.message = message;
        this.status = status;
    }
}
