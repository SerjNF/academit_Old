package ru.inbox.foreman.exception;

public class MyListIsEmptyException extends RuntimeException {
    public MyListIsEmptyException(String message){
        super(message);
    }
}
