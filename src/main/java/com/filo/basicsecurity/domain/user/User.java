package com.filo.basicsecurity.domain.user;

import com.filo.basicsecurity.domain.authority.Authority;
import com.filo.basicsecurity.common.enums.EncryptionAlgorithm;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class User {
    private String username;
    private String password;
    private EncryptionAlgorithm algorithm;
    private List<Authority> authorities;
}