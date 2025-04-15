package com.teka.shared.error;

import lombok.Getter;

@Getter
public abstract class TekaException extends RuntimeException {

    private final ErrorProperty errorProperty;

    public TekaException(ErrorProperty errorProperty) {
        super(errorProperty.getMessage());
        this.errorProperty = errorProperty;
    }
}
