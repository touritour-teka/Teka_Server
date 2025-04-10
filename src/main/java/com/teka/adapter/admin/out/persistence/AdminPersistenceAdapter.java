package com.teka.adapter.admin.out.persistence;


import com.teka.application.admin.port.out.CheckAdminUsernamePort;
import com.teka.application.admin.port.out.SaveAdminPort;
import com.teka.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminPersistenceAdapter implements SaveAdminPort, CheckAdminUsernamePort {

    private final AdminRepository adminRepository;

    @Override
    public void save(Admin admin) {
        AdminJpaEntity entity = AdminJpaEntity.from(admin);
        adminRepository.save(entity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}
