package com.koc.user.adapter.in.rest;

import com.koc.user.application.port.in.GetUserUseCase;
import com.koc.user.application.port.in.WithdrawUseCase;
import com.koc.user.domain.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final GetUserUseCase getUserUseCase;
    private final WithdrawUseCase withdrawUseCase;

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
