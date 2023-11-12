package com.underlake.honey.dao;

import com.underlake.honey.utils.JsonListType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductEntity {

    public static ProductEntity fromGraphQLInput(Map<String, Object> input) {
        Map<String, Object> unwrapped = (Map<String, Object>) input.get("product");
        ProductEntity entity = new ProductEntity();
        Object title = unwrapped.get("title");
        Objects.requireNonNull(title, "Title not supplied");
        Object imageUrl = unwrapped.get("imageUrl");
        Objects.requireNonNull(imageUrl, "Image url not supplied");
        Object description = unwrapped.get("description");
        Objects.requireNonNull(description, "Description not supplied");

        List<String> types = (List<String>)unwrapped.get("types");
        Objects.requireNonNull(types, "Types not supplied");
        List<String> volumes = (List<String>)unwrapped.get("volumes");
        Objects.requireNonNull(volumes, "Volumes not supplied");
        if (!(title instanceof String && description instanceof String && imageUrl instanceof String)) {
            throw new IllegalArgumentException("Some parameter is of incorrect type");
        }
        entity.setTitle(title.toString());
        entity.setDescription(description.toString());
        entity.setImageUrl(imageUrl.toString());
        entity.setTypes(types);
        entity.setVolumes(volumes);
        return entity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_gen")
    @SequenceGenerator(name = "product_gen", sequenceName = "product_seq", allocationSize = 1)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String description;

    @Type(JsonListType.class)
    @Column
    private List<String> types;

    @Column
    @Type(JsonListType.class)
    private List<String> volumes;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTypes() {
        return types;
    }

    public List<String> getVolumes() {
        return volumes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public void setVolumes(List<String> volumes) {
        this.volumes = volumes;
    }
}
