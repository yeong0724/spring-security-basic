package com.filo.basicsecurity.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationProviderService implements AuthenticationProvider {
    private final JpaUserDetailService jpaUserDetailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SCryptPasswordEncoder sCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        /**
         * AuthenticationProvider 에서는 JPA 에 연결하는 DetailService(JpaUserDetailService) 를 활용 하고 있고,
         * CustomUserDetails 는 내부에서 사용하는 User 객체 구현한다.
         */
        CustomUserDetails user = (CustomUserDetails) jpaUserDetailService.loadUserByUsername(username);

        return switch (user.getUser().getAlgorithm()) {
            case BCRYPT -> checkPassword(user, password, bCryptPasswordEncoder);
            case SCRYPT -> checkPassword(user, password, sCryptPasswordEncoder);
        };
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder passwordEncoder) {
        if (passwordEncoder.matches(rawPassword, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword(),
                    user.getAuthorities()
            );
        }

        throw new BadCredentialsException("Bad credentials");
    }
}