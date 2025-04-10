package com.teka.application.admin.exception;

import com.teka.application.admin.exception.error.AdminApplicationErrorProperty;
import com.teka.shared.error.TekaException;

public class DuplicateAdminIdException extends TekaException {

    public DuplicateAdminIdException() {
        super(AdminApplicationErrorProperty.DUPLICATE_ADMIN_ID);
    }
}
