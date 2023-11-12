package com.underlake.honey.dto;

import com.underlake.honey.dao.ArticleEntity;
import com.underlake.honey.utils.ValidationUtils;

public record Article(String title, String href) {

    public Article(ArticleEntity entity) {
        this(entity.getTitle(), entity.getHref());
    }

    public Article {
        ValidationUtils.requireTextNotEmpty(title);
        ValidationUtils.requireTextNotEmpty(href);
    }
}
