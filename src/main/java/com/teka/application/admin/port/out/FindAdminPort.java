package com.teka.application.admin.port.out;

import com.teka.domain.admin.Admin;

import java.util.Optional;

public interface FindAdminPort {
    Optional<Admin> findByUsername(String username);
}
