package com.koc.user.application.service;

import com.koc.common.exception.NotFoundException;
import com.koc.common.exception.PasswordNotMatchException;
import com.koc.common.exception.UserNotFoundException;
import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import com.koc.user.application.port.out.*;
import com.koc.user.domain.token.TokenDto;
import com.koc.user.domain.token.TokenService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase, CheckAccessTokenUseCase {
    private final LoadTokenByEmailPort loadTokenByEmailPort;
    private final LoadUserByEmailPort loadUserByEmailPort;
    private final SaveTokenPort saveTokenPort;
    private final TokenService tokenService;


    private static final int ACCESS_TOKEN_VALID_TIME = 30;
    private static final int REFRESH_TOKEN_VALID_TIME = 300;

    @Override
    public TokenDto login(final String email, final String password) throws PasswordNotMatchException, UserNotFoundException {
        var userDto = loadUserByEmailPort.load(email);

        return userDto.map(user -> {
            if(user.getPassword().equals(password)) {
                return createToken(email);
            } else {
                throw new PasswordNotMatchException();
            }
        }).orElseThrow(UserNotFoundException::new);
    }

    public TokenDto createToken(String email) {
        String accessToken = JwtProvider.createToken(email, ACCESS_TOKEN_VALID_TIME);
        String refreshToken = JwtProvider.createToken(email, REFRESH_TOKEN_VALID_TIME);

        saveRefreshToken(email, refreshToken);

        return new TokenDto(null, accessToken, refreshToken, email);
    }

    public void saveRefreshToken(String userEmail, String refreshToken) {
        var tokenDto = loadTokenByEmailPort.load(userEmail).orElseThrow(RuntimeException::new);
        tokenDto = tokenService.updateRefreshToken(tokenDto, refreshToken);
        saveTokenPort.save(tokenDto);
    }

    @Override
    public TokenDto check(String accessToken, String email) throws NotFoundException, ExpiredJwtException {
        JwtProvider.parseJwtToken(accessToken);
        return new TokenDto(null, null, accessToken, email);
    }
}
