package com.koc.user.domain;

import com.koc.user.adapter.out.persistence.UserRepository;
import com.koc.user.infrastructure.Entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByKakaoId(Long Id) {
        Optional<UserEntity> entity = userRepository.findByKakaoId(Id);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entity.get().toUser());
    }

    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entity = userRepository.findByEmail(email);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entity.get().toUser());
    }

    public Optional<User> findById(Long Id) {
        Optional<UserEntity> entity = userRepository.findById(Id);
        if (entity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entity.get().toUser());
    }

    public User save(User user) {
        return userRepository.save(user.toEntity()).toUser();
    }


}
