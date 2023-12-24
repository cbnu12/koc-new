package com.koc.user.adapter.in.rest;

import com.koc.common.exception.ValidationException;
import com.koc.user.application.port.in.ChangePasswordUseCase;
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
@RequestMapping("user")
public class UserController {
    private final SignUpUseCase signUpUseCase;
    private final GetUserUseCase getUserUseCase;
    private final WithdrawUseCase withdrawUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    @PostMapping
    public void signUp(@RequestBody SignUpRequest request) {
        try {
            signUpUseCase.signUp(request.email(), request.password());
        } catch (ValidationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public void withdraw(@PathVariable("id") Long id) {
        withdrawUseCase.withdraw(id);
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return getUserUseCase.getUser(id);
    }

    @PatchMapping({"id"})
    public void changePassword(@PathVariable("id") Long id,
            @RequestBody ChangePasswordRequest request) {
        changePasswordUseCase.change(id, request.password());
    }
}
