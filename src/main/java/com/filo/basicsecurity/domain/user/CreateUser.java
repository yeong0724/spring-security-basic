package com.filo.basicsecurity.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUser {
    private final String username;
    private final String password;
}