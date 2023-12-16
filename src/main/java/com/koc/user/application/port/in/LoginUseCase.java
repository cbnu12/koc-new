package com.koc.user.application.port.in;

import com.koc.common.exception.PasswordNotMatchException;
import com.koc.common.exception.UserNotFoundException;
import com.koc.user.domain.token.TokenDto;

public interface LoginUseCase {
    TokenDto login(final String email, final String password) throws PasswordNotMatchException, UserNotFoundException;
}
