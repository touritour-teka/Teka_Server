package com.teka.application.auth.service;

import com.teka.application.admin.exception.AdminNotFoundException;
import com.teka.application.admin.port.out.FindAdminPort;
import com.teka.application.auth.port.out.TokenProvider;
import com.teka.application.user.exception.UserNotFoundException;
import com.teka.application.user.port.out.FindUserPort;
import com.teka.domain.admin.Admin;
import com.teka.domain.user.User;
import com.teka.domain.user.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthFacade {

    private final FindAdminPort findAdminPort;
    private final FindUserPort findUserPort;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public Admin getAdmin(String token) {
        return findAdminPort.findByUsername(tokenProvider.getUuid(token))
                .orElseThrow(AdminNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public User getUser(String token) {
        return findUserPort.findById(new UserId(Long.valueOf(tokenProvider.getUuid(token))))
                .orElseThrow(UserNotFoundException::new);
    }
}
