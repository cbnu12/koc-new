package com.koc.user.application.service;

import com.koc.common.exception.NotFoundException;
import com.koc.user.adapter.out.api.kakao.KakaoClient;
import com.koc.user.adapter.out.api.kakao.KakaoToken;
import com.koc.user.adapter.out.api.kakao.KakaoUserInfo;
import com.koc.user.application.port.in.CheckAccessTokenUseCase;
import com.koc.user.application.port.in.GetKakaoLoginUrlUseCase;
import com.koc.user.application.port.in.LoginUseCase;
import com.koc.user.application.port.out.LoadTokenByEmailPort;
import com.koc.user.application.port.out.LoadUserByKakaoId;
import com.koc.user.application.port.out.SaveTokenPort;
import com.koc.user.application.port.out.SaveUserPort;
import com.koc.user.domain.token.TokenDto;
import com.koc.user.domain.token.TokenService;
import com.koc.user.domain.user.LoginType;
import com.koc.user.domain.user.UserDto;
import com.koc.user.domain.user.UserStatus;
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
    private final LoadUserByKakaoId loadUserByKakaoId;
    private final LoadTokenByEmailPort loadTokenByEmailPort;
    private final SaveTokenPort saveTokenPort;
    private final SaveUserPort saveUserPort;
    private final TokenService tokenService;
    private static final int accessTokenValidTime = 30;
    private static final int refreshTokenValidTime = 300;
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
        log.debug("get kakao token : {}", kakaoToken.toString());
        KakaoUserInfo info = getKakaoUserInfo(kakaoToken.getAccessToken());
        log.debug("get kakao user info : {}", info.toString());
        Optional<UserDto> userDto = loadUserByKakaoId.loadByKakaoId(info.getId());
        if (userDto.isEmpty()) {
            kakaoJoin(info);
        }
        return createToken(info.getEmail());
    }

    public TokenDto createToken(String email) {
        String accessToken = JwtProvider.createToken(email, accessTokenValidTime);
        String refreshToken = JwtProvider.createToken(email, refreshTokenValidTime);

        saveRefreshToken(email, refreshToken);

        return new TokenDto(null, accessToken, refreshToken, email);
    }

    public void saveRefreshToken(String userEmail, String refreshToken) {
        var tokenDto = loadTokenByEmailPort.load(userEmail).orElseThrow(RuntimeException::new);
        tokenDto = tokenService.updateRefreshToken(tokenDto, refreshToken);
        saveTokenPort.save(tokenDto);
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

    public void kakaoJoin(KakaoUserInfo kakaoUserInfo) {
        var userDto = UserDto.builder()
                .kakaoId(kakaoUserInfo.getId())
                .email(kakaoUserInfo.getEmail())
                .loginType(LoginType.KAKAO)
                .userStatus(UserStatus.ACTIVE)
                .build();

        saveUserPort.save(userDto);
    }

    @Override
    public TokenDto check(String accessToken, String email) throws NotFoundException, ExpiredJwtException {
        JwtProvider.parseJwtToken(accessToken);
        return new TokenDto(null, null, accessToken, email);
    }

    @Override
    public String getUrl() {
        return kakaoOauthUri + "/authorize" +
                "?client_id=" + javascriptKey +
                "&redirect_uri=" + redirectUri +
                "&response_type=code";
    }
}
