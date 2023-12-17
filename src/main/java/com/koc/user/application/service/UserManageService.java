package com.koc.user.application.service;

import com.koc.user.application.port.in.GetUserUseCase;
import com.koc.user.application.port.in.SignUpUseCase;
import com.koc.user.application.port.in.WithdrawUseCase;
import com.koc.user.application.port.out.LoadUserPort;
import com.koc.user.application.port.out.SaveUserPort;
import com.koc.user.domain.user.UserDto;
import com.koc.user.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserManageService implements GetUserUseCase, WithdrawUseCase, SignUpUseCase {
    private final UserService userService;
    private final SaveUserPort saveUserPort;
    private final LoadUserPort loadUserPort;

    @Override
    public void withdraw(Long id) {
        var userDto = loadUserPort.load(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재 하지 않습니다."));
        userDto = userService.withdraw(userDto);
        saveUserPort.save(userDto);
    }

    @Override
    public UserDto getUser(Long id) {
        return loadUserPort.load(id).orElseThrow(() -> new IllegalArgumentException("없는 사용자"));
    }

    @Override
    public void signUp(String email, String password) {
        var userDto = userService.create(email, password);
        saveUserPort.save(userDto);
    }
}
