package com.koc.user.domain.vo;

public record Password(String value) implements Validatable {
    @Override
    public boolean validate() {
        return value.length() > 8 && value.length() < 20;
    }
}
