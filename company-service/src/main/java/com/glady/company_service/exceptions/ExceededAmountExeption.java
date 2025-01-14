package com.glady.company_service.exceptions;

public class ExceededAmountExeption extends RuntimeException{
    public ExceededAmountExeption(String message){
        super(message);
    }
}
