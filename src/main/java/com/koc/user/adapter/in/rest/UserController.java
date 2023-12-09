package com.koc.user.adapter.in.rest;

import com.koc.user.domain.UserDto;
import com.koc.user.domain.UserService;
import com.koc.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/health-check")
    public Long healthCheck() {
        return System.currentTimeMillis();
    }

    @PutMapping("/withdraw")
    public User withdraw(@RequestBody UserDto userDto) {
        return userService.withdraw(userDto.getId());
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable long id) {
        return userService.findById(id);
    }


}
