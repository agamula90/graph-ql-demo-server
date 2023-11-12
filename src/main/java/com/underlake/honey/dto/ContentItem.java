package com.underlake.honey.dto;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.utils.ValidationUtils;

public record ContentItem(int id, String text, String imageUrl, String imageDescription) {

    public ContentItem(ContentEntity entity) {
        this(entity.getId(), entity.getText(), entity.getImageUrl(), entity.getImageDescription());
    }

    public ContentItem {
        ValidationUtils.requireTextNotEmpty(text);
        ValidationUtils.requireTextNotEmpty(imageUrl);
        ValidationUtils.requireTextNotEmpty(imageDescription);
    }
}
