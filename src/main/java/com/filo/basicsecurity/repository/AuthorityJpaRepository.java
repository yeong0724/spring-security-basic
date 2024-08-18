package com.filo.basicsecurity.repository;

import com.filo.basicsecurity.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityJpaRepository extends JpaRepository<AuthorityEntity, Integer> {
}