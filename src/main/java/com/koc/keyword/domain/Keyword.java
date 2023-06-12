package com.koc.keyword.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Keyword {
    String text;
    Long count;
}
