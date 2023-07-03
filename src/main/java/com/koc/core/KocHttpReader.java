package com.koc.core;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KocHttpReader {
    private final HttpServletRequest request;

    public Optional<Long> findUserId() {
        return Optional.ofNullable(request.getHeader("X-USER-ID")).map(Long::parseLong);
    }
}
