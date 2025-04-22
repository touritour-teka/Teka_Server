package com.teka.application.user.port.out;

import com.teka.domain.user.User;
import com.teka.domain.user.UserId;

import java.util.Optional;

public interface FindUserPort {
    Optional<User> findById(UserId userId);
}
