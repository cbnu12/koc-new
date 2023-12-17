package com.koc.user.adapter.in.rest;

import com.koc.common.exception.ValidationException;
import com.koc.user.application.port.in.GetUserUseCase;
import com.koc.user.application.port.in.SignUpUseCase;
import com.koc.user.application.port.in.WithdrawUseCase;
import com.koc.user.domain.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final SignUpUseCase signUpUseCase;
    private final GetUserUseCase getUserUseCase;
    private final WithdrawUseCase withdrawUseCase;

    @PostMapping
    public void signUp(SignUpRequest request) {
        try {
            signUpUseCase.signUp(request.email(), request.password());
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/health-check")
    public Long healthCheck() {
        return System.currentTimeMillis();
    }

    @DeleteMapping("/withdraw")
    public void withdraw(@RequestBody Long id) {
        withdrawUseCase.withdraw(id);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable long id) {
        return getUserUseCase.getUser(id);
    }
}
