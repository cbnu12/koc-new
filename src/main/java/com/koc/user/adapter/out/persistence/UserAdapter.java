package com.koc.user.adapter.out.persistence;

import com.koc.user.application.port.out.LoadUserPort;
import com.koc.user.application.port.out.SaveUserPort;
import com.koc.user.domain.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAdapter implements SaveUserPort, LoadUserPort {
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> load(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(UserDto dto) {
        var entity = UserEntity.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .userStatus(dto.getUserStatus())
                .kocId(dto.getKocId())
                .pw(dto.getPassword())
                .refreshToken(dto.getRefreshToken())
                .build();
        userRepository.save(entity);
    }
}
