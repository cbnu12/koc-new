package com.koc.user.application.port.in;

public interface ChangePasswordUseCase {
    void change(Long id, String newPassword);
}
