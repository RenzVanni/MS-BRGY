package com.ms_spring_brgy.officials.exception;

public class DoesNotExistsException extends NoSuchFieldException{
    public DoesNotExistsException(String s) {
        super(s);
    }
}
