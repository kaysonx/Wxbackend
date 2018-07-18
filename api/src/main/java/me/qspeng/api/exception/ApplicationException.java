package me.qspeng.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public abstract class ApplicationException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
