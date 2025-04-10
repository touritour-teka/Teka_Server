package com.teka.adapter.admin.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminJpaEntity, Integer> {
    boolean existsByUsername(String username);
    Optional<AdminJpaEntity> findByUsername(String username);
}
