package me.qspeng.api.exception.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@AllArgsConstructor
@JsonInclude(NON_EMPTY)
public class ApiErrors {
    private List<ApiError> errors;
}
