package com.pnc.gamestore.exception;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException(String message) {
        super(message);
    }

    public DuplicateEntityException(String entityName, String field, Object value) {
        super(entityName + " already exists with " + field + ": " + value);
    }
}
