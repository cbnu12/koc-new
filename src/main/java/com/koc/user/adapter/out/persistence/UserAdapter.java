package com.koc.user.adapter.out.persistence;

import com.koc.user.application.port.out.LoadUserByEmailPort;
import com.koc.user.application.port.out.LoadUserPort;
import com.koc.user.application.port.out.SaveUserPort;
import com.koc.user.domain.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPort, LoadUserPort, LoadUserByEmailPort {
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> load(Long id) {
        return userRepository.findById(id).map(UserDto::fromEntity);
    }

    @Override
    @Transactional
    public void save(UserDto dto) {
        var entity = UserEntity.builder()
                .id(dto.id())
                .email(dto.email())
                .password(dto.password())
                .userStatus(dto.userStatus())
                .build();
        userRepository.save(entity);
    }

    @Override
    public Optional<UserDto> load(String email) {
        return userRepository.findByEmail(email).map(UserDto::fromEntity);
    }
}
