package com.koc.theme.application.port.in;

import com.koc.place.domain.Place;
import com.koc.theme.domain.Theme;

public interface ThemeAddPlaceUseCase {
    void addPlace(Theme theme, Place place);
}
