package com.teka.application.auth.service;

import com.teka.application.admin.exception.AdminNotFoundException;
import com.teka.application.admin.port.out.FindAdminPort;
import com.teka.application.auth.port.in.LogOutAdminUseCase;
import com.teka.application.auth.port.out.DeleteTokenPort;
import com.teka.domain.admin.Admin;
import com.teka.domain.admin.AdminId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogOutAdminService implements LogOutAdminUseCase {

    private final DeleteTokenPort deleteTokenPort;
    private final FindAdminPort findAdminPort;

    @Override
    public void execute(AdminId adminId) {
        Admin admin = findAdminPort.findById(adminId)
                .orElseThrow(AdminNotFoundException::new);
        deleteTokenPort.deleteById(admin.getUsername());
    }
}
