package com.teka.application.auth.port.in;

import com.teka.domain.admin.AdminId;

public interface LogOutAdminUseCase {
    void execute(AdminId adminId);
}
