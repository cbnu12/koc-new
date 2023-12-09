package com.koc.user.adapter.out.persistence;

import com.koc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPort {
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
