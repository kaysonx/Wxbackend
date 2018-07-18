package me.qspeng.api.exception.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Builder
@JsonInclude(NON_EMPTY)
public class ApiError {
    private String status;
    private String code;
    private String title;
    private String detail;
}
