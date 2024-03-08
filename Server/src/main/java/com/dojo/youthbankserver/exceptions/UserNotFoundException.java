package com.dojo.youthbankserver.exceptions;
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
