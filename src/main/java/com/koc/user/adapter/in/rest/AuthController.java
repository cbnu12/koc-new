package com.koc.user.adapter.in.rest;

import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.GetKakaoLoginUrlUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import com.koc.user.domain.token.TokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final CheckAccessTokenUseCase checkAccessTokenUseCase;
    private final GetKakaoLoginUrlUseCase getKakaoLoginUrlUseCase;

    @GetMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam String code) {
        try {
            TokenDto result = loginUseCase.login(code);
            return ResponseEntity.ok(TokenResponse.of(result.refreshToken(), result.accessToken(), result.key()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/checkAccessToken")
    public TokenCheckResponse checkToken(@RequestHeader(value = "Authorization") String token, @RequestParam String email) {
        var result = checkAccessTokenUseCase.check(token, email);
        return TokenCheckResponse.of(result.accessToken());
    }

    @GetMapping("/kakao-login-url")
    public String kakaoLogin() {
        return getKakaoLoginUrlUseCase.getUrl();
    }

}
