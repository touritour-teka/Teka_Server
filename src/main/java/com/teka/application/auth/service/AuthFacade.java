package com.teka.application.auth.service;

import com.teka.application.admin.exception.AdminNotFoundException;
import com.teka.application.admin.port.out.FindAdminPort;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthFacade {

    private final FindAdminPort findAdminPort;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public Admin getAdmin(String username) {
        return findAdminPort.findByUsername(tokenProvider.getUuid(username))
                .orElseThrow(AdminNotFoundException::new);
    }
}
