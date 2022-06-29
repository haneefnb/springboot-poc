package com.haneefs.springbootpoc.exception;

public class RecordNotFoundException extends Exception{
    public RecordNotFoundException(String msg){
        super(msg);
    }
}
