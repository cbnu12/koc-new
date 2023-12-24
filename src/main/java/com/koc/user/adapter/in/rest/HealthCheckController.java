package com.koc.user.adapter.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("health-check")
public class HealthCheckController {
    @GetMapping
    public Long healthCheck() {
        return System.currentTimeMillis();
    }
}
