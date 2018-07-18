package me.qspeng.api.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class ResourceConflictException extends ApplicationException {
    public ResourceConflictException(String message) {
        super(CONFLICT, message);
    }
}
