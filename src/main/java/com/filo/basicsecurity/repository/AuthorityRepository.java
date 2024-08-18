package com.filo.basicsecurity.repository;

import com.filo.basicsecurity.domain.authority.Authority;
import com.filo.basicsecurity.entity.AuthorityEntity;
import com.filo.basicsecurity.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class AuthorityRepository {
    private final AuthorityJpaRepository authorityJpaRepository;

    @Transactional
    public Authority create(String name, UserEntity user) {
        return authorityJpaRepository.save(new AuthorityEntity(name, user)).toAuthority();
    }
}