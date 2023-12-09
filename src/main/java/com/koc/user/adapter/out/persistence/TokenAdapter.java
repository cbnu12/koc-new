package com.koc.user.adapter.out.persistence;

import com.koc.user.application.port.out.LoadTokenByEmailPort;
import com.koc.user.application.port.out.SaveTokenPort;
import com.koc.user.domain.token.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenAdapter implements SaveTokenPort, LoadTokenByEmailPort {
    private final UserTokenRepository userTokenRepository;

    @Override
    public Optional<TokenDto> load(String email) {
        return userTokenRepository.findByEmail(email)
                .map(it -> new TokenDto(it.getId(), it.getRefreshToken(), null, it.getEmail()));
    }

    @Override
    public void save(TokenDto dto) {
        var entity = UserTokenEntity.builder()
                .refreshToken(dto.refreshToken())
                .email(dto.key())
                .build();
        userTokenRepository.save(entity);
    }
}
