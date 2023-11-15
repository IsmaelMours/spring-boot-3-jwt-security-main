package com.alibou.security.Blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String ResourceName;
    String FieldName;
    long FieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue){
        super(String.format("%s not found with %s", resourceName,fieldName,fieldValue));
        this.FieldName = fieldName;
        this.ResourceName = resourceName;
        this.FieldValue = fieldValue;
    }
}
