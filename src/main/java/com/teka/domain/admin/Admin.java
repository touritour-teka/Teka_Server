package com.teka.domain.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Admin {

    private final AdminId id;

    private String username;

    private Password password;
}
