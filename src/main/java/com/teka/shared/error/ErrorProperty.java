package com.teka.shared.error;

import org.springframework.http.HttpStatus;

public interface ErrorProperty {

    HttpStatus getStatus();
    String getMessage();
    String name();
}
