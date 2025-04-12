package com.teka.application.admin.port.out;

import com.teka.domain.admin.Admin;
import com.teka.domain.admin.AdminId;

import java.util.Optional;

public interface FindAdminPort {
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findById(AdminId id);
}
