package com.koc.theme.application.service;

import com.koc.theme.application.port.in.ThemeCreateCommand;
import com.koc.theme.application.port.in.ThemeCreateUseCase;
import com.koc.theme.application.port.in.ThemeUpdateCommand;
import com.koc.theme.application.port.in.ThemeUpdateUseCase;
import com.koc.theme.application.port.out.ThemeSavePort;
import com.koc.theme.application.port.out.ThemeSearchPort;
import com.koc.theme.domain.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeCommandService implements ThemeCreateUseCase, ThemeUpdateUseCase {
    private final ThemeSearchPort searchPort;
    private final ThemeSavePort savePort;

    @Override
    public Long create(ThemeCreateCommand command) {
        Theme theme = command.toTheme();
        Theme result = savePort.save(theme);
        return result.getId();
    }

    @Override
    public Long update(Long id, ThemeUpdateCommand command) {
        Theme theme = searchPort.searchById(id);
        theme.update(command);
        Theme result = savePort.save(theme);
        return result.getId();
    }
}
