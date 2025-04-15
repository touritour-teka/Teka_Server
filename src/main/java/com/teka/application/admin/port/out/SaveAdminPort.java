package com.teka.application.admin.port.out;

import com.teka.domain.admin.Admin;

public interface SaveAdminPort {
    Long save(Admin admin);
}
