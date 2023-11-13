package com.underlake.honey.domain;

import com.underlake.honey.utils.ValidationUtils;

public record SupplementaryContentItem(int id, String text, String imageUrl, String imageDescription) {

    public SupplementaryContentItem {
        ValidationUtils.requireTextNotEmpty(text);
        ValidationUtils.requireTextNotEmpty(imageUrl);
        ValidationUtils.requireTextNotEmpty(imageDescription);
    }
}
