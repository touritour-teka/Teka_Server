package com.teka.application.admin.exception;

import com.teka.application.admin.exception.error.AdminApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class AdminIdAlreadyExistsException extends TekaException {

    public AdminIdAlreadyExistsException() {
        super(AdminApplicationErrorProperty.ADMIN_ID_ALREADY_EXISTS);
    }
}
