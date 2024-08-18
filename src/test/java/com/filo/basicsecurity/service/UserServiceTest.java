package com.filo.basicsecurity.service;

import com.filo.basicsecurity.domain.user.CreateUser;
import com.filo.basicsecurity.domain.user.User;
import com.filo.basicsecurity.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("user 가 존재하면 RuntimeException 을 발생시킨다.")
    void registerFailureCase() {
        // given
        CreateUser createUser = new CreateUser(
                "kid4211",
                "1234"
        );

        given(userRepository.userExists(createUser.getUsername())).willReturn(true);

        // when & then
        RuntimeException runtimeException = assertThrows(
                RuntimeException.class,
                () -> userService.register(createUser)
        );

        Assertions.assertEquals(runtimeException.getMessage(), "User [kid4211] already exists");
    }

    @Test
    @DisplayName("user 가 존재하지 않으면 회원가입을 수행한다.")
    void registerSuccessCase() {
        // given
        CreateUser createUser = new CreateUser("kjs8411", "12345");

        given(userRepository.userExists(createUser.getUsername())).willReturn(false);
        given(userRepository.create(createUser))
                .willReturn(User.builder().username(createUser.getUsername()).build());

        // when
        String register = userService.register(createUser);

        // then
        Assertions.assertEquals(register, createUser.getUsername());
        verify(userRepository, times(1)).create(createUser);
    }
}