package com.teka.domain.admin;

import com.teka.shared.util.PasswordUtil;
import lombok.Getter;

@Getter
public class Admin {

    private final AdminId id;

    private String username;

    private Password password;

    public Admin(AdminId id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = new Password(PasswordUtil.encode(password));
    }

    public Admin(AdminId id, String username, Password password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
