package com.filo.basicsecurity.repository;

import com.filo.basicsecurity.common.exception.UserNotFoundException;
import com.filo.basicsecurity.domain.user.CreateUser;
import com.filo.basicsecurity.domain.user.User;
import com.filo.basicsecurity.entity.AuthorityEntity;
import com.filo.basicsecurity.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final AuthorityJpaRepository authorityJpaRepository;

    @Transactional(readOnly = true)
    public Boolean userExists(String username) {
        return userJpaRepository.findUserByUsername(username).isPresent();
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        return userJpaRepository.findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new)
                .toUser();
    }

    @Transactional
    public User create(CreateUser create) {
        UserEntity userEntity = userJpaRepository.save(UserEntity.newUser(create));
        AuthorityEntity authorityEntity = new AuthorityEntity("READ", userEntity);
        AuthorityEntity authority = authorityJpaRepository.save(authorityEntity);

        userEntity.replaceAuthority(List.of(authority));
        return userEntity.toUser();
    }
}