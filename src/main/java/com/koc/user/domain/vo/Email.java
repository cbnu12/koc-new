package com.koc.user.domain.vo;

import java.util.regex.Pattern;

public record Email(String value) implements Validatable {
    @Override
    public boolean validate() {
        final String emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
        return Pattern.compile(emailPattern)
                .matcher(value)
                .matches();
    }
}
