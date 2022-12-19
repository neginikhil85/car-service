package com.learning.exceptions;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message){
        super(message);
    }
}
