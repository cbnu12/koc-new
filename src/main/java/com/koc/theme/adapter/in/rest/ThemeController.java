package com.koc.theme.adapter.in.rest;

import com.koc.theme.application.port.in.ThemeCreateUseCase;
import com.koc.theme.application.port.in.ThemePagingSearchUseCase;
import com.koc.theme.application.port.in.ThemeUpdateUseCase;
import com.koc.theme.domain.Theme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class ThemeController {
    private final ThemePagingSearchUseCase themeSearchUseCase;
    private final ThemeCreateUseCase themeCreateUseCase;
    private final ThemeUpdateUseCase themeUpdateUseCase;

    @GetMapping("/themes")
    public ThemeSearchPageResponse search(@Valid final ThemeSearchRequest request) {
        Page<Theme> themes = themeSearchUseCase.search(request.toThemeSearchQuery());
        return ThemeSearchPageResponse.from(themes);
    }

    @PostMapping("/themes")
    public Long create(@RequestBody @Valid final ThemeCreateRequest request) {
        return themeCreateUseCase.create(request.toCommand());
    }

    @PutMapping("/themes/{id}")
    public Long update(
            @PathVariable(name = "id") final Long id,
            @RequestBody @Valid final ThemeUpdateRequest request
    ) {
        return themeUpdateUseCase.update(id, request.toCommand());
    }

}
