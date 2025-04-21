package com.teka.application.user.port.out;

import com.teka.domain.user.User;

public interface SaveUserPort {
    void save(User user);
}
