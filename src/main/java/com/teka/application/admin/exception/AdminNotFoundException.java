package com.teka.application.admin.exception;

import com.teka.application.admin.exception.error.AdminApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class AdminNotFoundException extends TekaException {

    public AdminNotFoundException() {
        super(AdminApplicationErrorProperty.ADMIN_NOT_FOUND);
    }
}
