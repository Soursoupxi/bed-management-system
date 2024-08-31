package com.example.demo.exception;

//继承RuntimeException
public class MyException extends RuntimeException{
    public MyException(String message) {
        super(message);
    }
}
