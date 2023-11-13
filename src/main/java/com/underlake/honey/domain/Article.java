package com.underlake.honey.domain;

import com.underlake.honey.utils.ValidationUtils;

public record Article(String title, String href) {

    public Article {
        ValidationUtils.requireTextNotEmpty(title);
        ValidationUtils.requireTextNotEmpty(href);
    }
}
