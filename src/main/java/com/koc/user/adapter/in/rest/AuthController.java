package com.koc.user.adapter.in.rest;

import com.koc.common.exception.PasswordNotMatchException;
import com.koc.common.exception.UserNotFoundException;
import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import com.koc.user.domain.token.TokenDto;
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
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/checkAccessToken")
    public TokenCheckResponse checkToken(@RequestHeader(value = "Authorization") String token, @RequestParam String email) {
        var result = checkAccessTokenUseCase.check(token, email);
        return TokenCheckResponse.of(result.accessToken());
    }

}
