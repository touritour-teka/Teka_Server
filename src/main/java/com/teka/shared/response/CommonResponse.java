package com.teka.shared.response;

import com.teka.shared.error.ErrorProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> ok(T data) {
        return new CommonResponse<>(
                "SUCCESS",
                "ok",
                data
        );
    }

    public static <T> CommonResponse<T> error(ErrorProperty errorProperty, T error) {
        return new CommonResponse<>(
                errorProperty.name(),
                errorProperty.getMessage(),
                error
        );
    }

    public static <T> CommonResponse<T> error(ErrorProperty errorProperty) {
        return new CommonResponse<>(
                errorProperty.name(),
                errorProperty.getMessage()
        );
    }

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
