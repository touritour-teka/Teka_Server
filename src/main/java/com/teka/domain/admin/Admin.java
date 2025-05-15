package com.teka.domain.admin;

import com.teka.shared.domain.AuditableDomain;
import com.teka.shared.util.PasswordUtil;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Admin extends AuditableDomain {

    private final AdminId id;

    private String username;

    private Password password;

    public Admin(AdminId id, String username, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.username = username;
        this.password = new Password(PasswordUtil.encode(password));
    }

    public Admin(AdminId id, String username, Password password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
