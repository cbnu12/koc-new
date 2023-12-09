package com.koc.user.domain;

import com.koc.user.adapter.out.persistence.UserTokenRepository;
import com.koc.user.infrastructure.Entity.UserTokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserTokenService {
    private final UserTokenRepository userTokenRepository;

    public Optional<UserToken> findByEmail(String email) {
        Optional<UserTokenEntity> entity = userTokenRepository.findByEmail(email);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entity.get().toUserToken());
    }

    public UserToken save(UserToken userToken) {
        return userTokenRepository.save(userToken.toEntity()).toUserToken();
    }

}
