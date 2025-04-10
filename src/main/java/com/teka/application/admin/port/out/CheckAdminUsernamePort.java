package com.teka.application.admin.port.out;

public interface CheckAdminUsernamePort {
    boolean existsByUsername(String username);
}
