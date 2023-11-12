package com.underlake.honey.dao;

import jakarta.persistence.*;

import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "content_item")
public class ContentEntity {

    public static ContentEntity supplementaryContentItemFromGraphQLInput(Map<String, Object> input) {
        ContentEntity entity = fromUnwrappedMap((Map<String, Object>) input.get("supplementaryContentItem"));
        entity.setSupplementary(true);
        return entity;
    }

    public static ContentEntity contentItemFromGraphQLInput(Map<String, Object> input) {
        ContentEntity entity = fromUnwrappedMap((Map<String, Object>) input.get("contentItem"));
        entity.setSupplementary(false);
        return entity;
    }

    private static ContentEntity fromUnwrappedMap(Map<String, Object> unwrapped) {
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

    @Id
    @SequenceGenerator(name = "content_item_gen", sequenceName = "content_item_seq", allocationSize = 1)
    @GeneratedValue(generator = "content_item_gen", strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String text;

    @Column(name = "image_description", nullable = false)
    private String imageDescription;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "is_supplementary", nullable = false)
    private Boolean isSupplementary;

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean isSupplementary() {
        return isSupplementary;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSupplementary(Boolean supplementary) {
        isSupplementary = supplementary;
    }
}
