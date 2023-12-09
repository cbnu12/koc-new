package com.koc.user.adapter.in.rest;

import com.koc.user.application.jwt.TokenCheckResponse;
import com.koc.user.application.jwt.TokenResponse;
import com.koc.user.application.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public TokenResponse login(@RequestParam String code) {
        return authService.login(code);
    }

    @GetMapping("/checkAcessToken")
    public TokenCheckResponse checkToken(@RequestHeader(value = "Authorization") String token, @RequestParam String email) throws Exception {

        return authService.checkToken(token, email);
    }

    @GetMapping("/kakao-login-url")
    public String kakaoLogin() {
        return authService.getKakaoLoginUrl();
    }

}
