package com.example.addressbook.exception;

public class AddressBookException extends RuntimeException{
    String message;

    public AddressBookException(String message) {
        this.message = message;
    }

    public AddressBookException(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
