package com.ms_spring_brgy.user.exception;

public class DoesNotExistsException extends NoSuchFieldException{
    public DoesNotExistsException(String s) {
        super(s);
    }
}
