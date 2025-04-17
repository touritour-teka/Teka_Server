package com.teka.application.user.port.out;

import com.teka.domain.user.UserId;
import com.teka.domain.user.type.UserType;

public interface ChangeUserPort {
    void changeUserType(UserId userId, UserType type);
}
