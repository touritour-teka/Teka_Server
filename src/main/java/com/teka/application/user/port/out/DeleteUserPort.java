package com.teka.application.user.port.out;

import com.teka.domain.user.UserId;

public interface DeleteUserPort {
    void deleteByUserId(UserId userId);
}
