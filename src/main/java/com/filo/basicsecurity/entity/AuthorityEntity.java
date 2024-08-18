package com.filo.basicsecurity.entity;

import com.filo.basicsecurity.domain.authority.Authority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity userEntity;

    public AuthorityEntity(String name, UserEntity userEntity) {
        this.name = name;
        this.userEntity = userEntity;
    }

    public Authority toAuthority() {
        return Authority.builder()
                .name(this.name)
                .build();
    }
}