package com.koc.user.adapter.in.rest;

import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.GetKakaoLoginUrlUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final LoginUseCase loginUseCase;
    private final CheckAccessTokenUseCase checkAccessTokenUseCase;
    private final GetKakaoLoginUrlUseCase getKakaoLoginUrlUseCase;

    @GetMapping("/login")
    public TokenResponse login(@RequestParam String code) {
        var result = loginUseCase.login(code);
        return TokenResponse.of(result.refreshToken(), result.accessToken(), result.key());
    }

    @GetMapping("/checkAccessToken")
    public TokenCheckResponse checkToken(@RequestHeader(value = "Authorization") String token, @RequestParam String email) throws Exception {
        var result = checkAccessTokenUseCase.check(token, email);
        return TokenCheckResponse.of(result.accessToken());
    }

    @GetMapping("/kakao-login-url")
    public String kakaoLogin() {
        return getKakaoLoginUrlUseCase.getUrl();
    }

}
