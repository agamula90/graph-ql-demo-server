package com.underlake.honey.service;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.domain.ContentItem;
import com.underlake.honey.domain.SupplementaryContentItem;
import com.underlake.honey.repository.ContentItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ContentItemService {

    private final ContentItemRepository contentItemRepository;

    public ContentItemService(ContentItemRepository contentItemRepository) {
        this.contentItemRepository = contentItemRepository;
    }

    public List<ContentItem> findContentItems() {
        List<ContentEntity> contentItems = contentItemRepository.findContentEntitiesByIsSupplementaryFalse();
        return contentItems.stream().map(this::contentItemFromEntity).collect(Collectors.toList());
    }

    public List<SupplementaryContentItem> findSupplementaryContentItems() {
        List<ContentEntity> contentItems = contentItemRepository.findContentEntitiesByIsSupplementaryTrue();
        return contentItems.stream().map(this::supplementaryContentItemFromEntity).collect(Collectors.toList());
    }

    private ContentItem contentItemFromEntity(ContentEntity entity) {
        return new ContentItem(entity.getId(), entity.getText(), entity.getImageUrl(), entity.getImageDescription());
    }

    private SupplementaryContentItem supplementaryContentItemFromEntity(ContentEntity entity) {
        return new SupplementaryContentItem(entity.getId(), entity.getText(), entity.getImageUrl(), entity.getImageDescription());
    }

    private ContentEntity entityFromContentItem(ContentItem contentItem) {
        ContentEntity entity = new ContentEntity();
        entity.setText(contentItem.text());
        entity.setImageUrl(contentItem.imageUrl());
        entity.setImageDescription(contentItem.imageDescription());
        entity.setSupplementary(false);
        return entity;
    }

    private ContentEntity entityFromSupplementaryContentItem(SupplementaryContentItem contentItem) {
        ContentEntity entity = new ContentEntity();
        entity.setText(contentItem.text());
        entity.setImageUrl(contentItem.imageUrl());
        entity.setImageDescription(contentItem.imageDescription());
        entity.setSupplementary(true);
        return entity;
    }

    public SupplementaryContentItem supplementaryContentItemFromGraphQLInput(Map<String, Object> input) {
        ContentEntity entity = contentEntityFromUnwrappedGraphQLInput((Map<String, Object>) input.get("supplementaryContentItem"));
        entity.setSupplementary(true);
        return supplementaryContentItemFromEntity(entity);
    }

    public ContentItem contentItemFromGraphQLInput(Map<String, Object> input) {
        ContentEntity entity = contentEntityFromUnwrappedGraphQLInput((Map<String, Object>) input.get("contentItem"));
        entity.setSupplementary(false);
        return contentItemFromEntity(entity);
    }

    private ContentEntity contentEntityFromUnwrappedGraphQLInput(Map<String, Object> unwrapped) {
        ContentEntity entity = new ContentEntity();
        Object text = unwrapped.get("text");
        Objects.requireNonNull(text, "Text not supplied");
        Object imageDescription = unwrapped.get("imageDescription");
        Objects.requireNonNull(imageDescription, "Image description not supplied");
        Object imageUrl = unwrapped.get("imageUrl");
        Objects.requireNonNull(imageUrl, "Image url not supplied");
        if (!(text instanceof String && imageDescription instanceof String && imageUrl instanceof String)) {
            throw new IllegalArgumentException("Some parameter is of incorrect type");
        }
        entity.setText(text.toString());
        entity.setImageDescription(imageDescription.toString());
        entity.setImageUrl(imageUrl.toString());
        return entity;
    }

    public ContentItem addContentItem(ContentItem contentItem) {
        ContentEntity entity = contentItemRepository.save(entityFromContentItem(contentItem));
        return contentItemFromEntity(entity);
    }

    public SupplementaryContentItem addSupplementaryContentItem(SupplementaryContentItem contentItem) {
        ContentEntity entity = contentItemRepository.save(entityFromSupplementaryContentItem(contentItem));
        return supplementaryContentItemFromEntity(entity);
    }

    public boolean deleteContentItemById(int id) {
        try {
            ContentEntity entity = contentItemRepository.findById(id).orElseThrow();
            contentItemRepository.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
