package com.underlake.honey.dto;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.utils.ValidationUtils;

public record SupplementaryItem(int id, String text, String imageUrl, String imageDescription) {

    public SupplementaryItem(ContentEntity entity) {
        this(entity.getId(), entity.getText(), entity.getImageUrl(), entity.getImageDescription());
    }

    public SupplementaryItem {
        ValidationUtils.requireTextNotEmpty(text);
        ValidationUtils.requireTextNotEmpty(imageUrl);
        ValidationUtils.requireTextNotEmpty(imageDescription);
    }
}
