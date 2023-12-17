package com.koc.user.domain.vo;

import java.util.regex.Pattern;

public record Email(String value) implements Validatable {
    @Override
    public boolean validate() {
        final String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*+@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*+(\\.[A-Za-z]{2,})$";
        return Pattern.compile(emailPattern)
                .matcher(value)
                .matches();
    }
}
