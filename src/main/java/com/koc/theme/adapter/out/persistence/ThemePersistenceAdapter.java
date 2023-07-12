package com.koc.theme.adapter.out.persistence;

import com.koc.theme.application.port.out.ThemeSavePort;
import com.koc.theme.application.port.out.ThemeSearchPort;
import com.koc.theme.domain.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThemePersistenceAdapter implements ThemeSearchPort, ThemeSavePort {
    private final ThemeRepository repository;

    @Override
    public Page<Theme> search(Integer page, Integer size, String name, String description) {
        Page<ThemeJpaEntity> entities = repository.findByNameLikeAndDescriptionLike("%%%s%%".formatted(name), "%%%s%%".formatted(description), PageRequest.of(page, size));
        return entities.map(ThemeJpaEntity::toTheme);
    }

    @Override
    public Theme searchById(Long id) {
        return null;
    }

    @Override
    public Theme save(Theme theme) {
        ThemeJpaEntity entity = repository.save(ThemeJpaEntity.from(theme));
        return entity.toTheme();
    }
}
