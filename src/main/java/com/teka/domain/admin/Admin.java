package com.teka.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Admin {

    private final AdminId id;

    private final String username;

    private final Password password;
}
