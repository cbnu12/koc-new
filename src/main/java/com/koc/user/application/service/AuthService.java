package com.koc.user.application.service;


import com.koc.common.exception.NotFoundException;
import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.GetKakaoLoginUrlUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import com.koc.user.domain.*;
import com.koc.user.adapter.out.api.kakao.KakaoClient;
import com.koc.user.adapter.out.api.kakao.KakaoToken;
import com.koc.user.adapter.out.api.kakao.KakaoUserInfo;
import com.koc.user.adapter.out.persistence.UserPort;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements LoginUseCase, CheckAccessTokenUseCase, GetKakaoLoginUrlUseCase {
    private final KakaoClient client;
    private final UserPort userPort;
    private final UserTokenService userTokenService;
    private static final int accessTokenValidTime = 30;
    private static final int refreshTokenValidTime = 300;
    private final JwtProvider jwtProvider;
    @Value("${social-login.kakao.oauth_uri}")
    private String kakaoOauthUri;
    @Value("${social-login.kakao.client_id}")
    private String clientId;
    @Value("${social-login.kakao.redirect_uri}")
    private String redirectUri;
    @Value("${social-login.kakao.kapi_user_info_uri}")
    private String kapiUserInfoUri;
    @Value("${social-login.kakao.javascript_key}")
    private String javascriptKey;

    @Override
    public TokenDto login(final String code) {
        KakaoToken kakaoToken = getToken(code);
        log.debug(kakaoToken.toString());
        KakaoUserInfo info = getKakaoUserInfo(kakaoToken.getAccessToken());
        log.debug(info.toString());
        Optional<User> user = userPort.findByKakaoId(info.getId());
        if (user.isEmpty()) {
            Optional.of(kakaoJoin(info));
        }
        return createToken(info.getEmail());
    }

    public TokenDto createToken(String email) {
        String accessToken = jwtProvider.createToken(email, accessTokenValidTime);
        String refreshToken = jwtProvider.createToken(email, refreshTokenValidTime);

        saveRefreshToken(email, refreshToken);

        return new TokenDto(accessToken, refreshToken, email);
    }

    public void saveRefreshToken(String userEmail, String refreshToken) {
        UserToken userToken = userTokenService.findByEmail(userEmail).orElseThrow(() -> new RuntimeException());
        userToken.setRefreshToken(refreshToken);
        userTokenService.save(userToken);
    }


    public KakaoToken getToken(final String code) {
        String kakaoTokenUri = kakaoOauthUri + "/token";
        try {
            return client.getToken(new URI(kakaoTokenUri), clientId, redirectUri, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);
            throw new RuntimeException("카카오 사용자 정보를 가져 오는중 오류가 발생했습니다.");
        }
    }

    public KakaoUserInfo getKakaoUserInfo(String token) {
        try {
            return client.getKakaoUserInfo(new URI(kapiUserInfoUri), "Bearer " + token);
        } catch (Exception e) {
            log.error("Something error..", e);
            throw new RuntimeException("카카오 인증중 오류가 발생했습니다.");
        }
    }

    public User kakaoJoin(KakaoUserInfo kakaoUserInfo) {
        KakaoUser kakaoUser = KakaoUser.builder().
                kakaoId(kakaoUserInfo.getId())
                .email(kakaoUserInfo.getEmail())
                .build();

        User user = User.builder()
                .kakaoUser(kakaoUser)
                .loginType(LoginType.KAKAO)
                .userStatus(UserStatus.ACTIVE)
                .build();

        return userPort.save(user);
    }

    @Override
    public TokenDto check(String accessToken, String email) {
        try {
            jwtProvider.parseJwtToken(accessToken);
        } catch (ExpiredJwtException e) {
            UserToken userToken = userTokenService.findByEmail(email)
                    .orElseThrow(() -> new NotFoundException("user token"));
            if (userToken.isExpire()) {
                throw e;
            }

            return new TokenDto(null, jwtProvider.createToken(email, accessTokenValidTime), email);
        }
        return new TokenDto(null ,accessToken, email);
    }

    @Override
    public String getUrl() {
        return kakaoOauthUri + "/authorize" +
                "?client_id=" + javascriptKey +
                "&redirect_uri=" + redirectUri +
                "&response_type=code";
    }
}
