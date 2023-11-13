package com.underlake.honey.dao;

import jakarta.persistence.*;

@Entity
@Table(name = "content_item")
public class ContentEntity {

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
