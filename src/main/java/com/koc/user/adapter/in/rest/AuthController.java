package com.koc.user.adapter.in.rest;

import com.koc.common.exception.NotFoundException;
import com.koc.common.exception.PasswordNotMatchException;
import com.koc.common.exception.UserNotFoundException;
import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import com.koc.user.domain.token.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final CheckAccessTokenUseCase checkAccessTokenUseCase;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        try {
            TokenDto result = loginUseCase.login(request.email(), request.password());
            return TokenResponse.of(result.refreshToken(), result.accessToken(), result.key());
        } catch (PasswordNotMatchException | UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/access-token/check")
    public void checkToken(@RequestBody CheckAccessTokenRequest request) {
        try {
            checkAccessTokenUseCase.check(request.token(), request.email());
        } catch (NotFoundException | ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}