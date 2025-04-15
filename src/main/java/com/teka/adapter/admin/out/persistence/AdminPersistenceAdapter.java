package com.teka.adapter.admin.out.persistence;


import com.teka.application.admin.port.out.CheckAdminUsernamePort;
import com.teka.application.admin.port.out.FindAdminPort;
import com.teka.application.admin.port.out.SaveAdminPort;
import com.teka.domain.admin.Admin;
import com.teka.domain.admin.AdminId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminPersistenceAdapter implements SaveAdminPort, CheckAdminUsernamePort, FindAdminPort {

    private final AdminRepository adminRepository;

    @Override
    public Long save(Admin admin) {
        AdminJpaEntity entity = AdminJpaEntity.from(admin);
        return adminRepository.save(entity).getId();
    }

    @Override
    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username)
                .map(AdminJpaEntity::toDomain);
    }

    @Override
    public Optional<Admin> findById(AdminId id) {
        return adminRepository.findById(id.value())
                .map(AdminJpaEntity::toDomain);
    }

    @Override
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}
