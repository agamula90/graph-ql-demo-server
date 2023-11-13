package com.underlake.honey.domain;

import com.underlake.honey.utils.ValidationUtils;

public record ContentItem(int id, String text, String imageUrl, String imageDescription) {

    public ContentItem {
        ValidationUtils.requireTextNotEmpty(text);
        ValidationUtils.requireTextNotEmpty(imageUrl);
        ValidationUtils.requireTextNotEmpty(imageDescription);
    }
}
