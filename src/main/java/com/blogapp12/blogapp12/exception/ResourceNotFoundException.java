package com.blogapp12.blogapp12.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue) {
        super(String.format("%s is not found with %s:'s'",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;

    }
}
