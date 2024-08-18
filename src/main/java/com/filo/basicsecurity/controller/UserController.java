package com.filo.basicsecurity.controller;

import com.filo.basicsecurity.controller.request.UserRegisterRequestBody;
import com.filo.basicsecurity.controller.response.ResultResponse;
import com.filo.basicsecurity.domain.user.CreateUser;
import com.filo.basicsecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/api/v1/register")
    public ResultResponse<String> register(@RequestBody UserRegisterRequestBody requestBody) {
        String username = requestBody.getUsername();
        String encodedPassword = bCryptPasswordEncoder.encode(requestBody.getPassword());
        CreateUser createUser = new CreateUser(username, encodedPassword);

        String result = userService.register(createUser);

        return ResultResponse.ok(result);
    }
}