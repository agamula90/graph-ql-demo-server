package com.underlake.honey.domain;

import com.underlake.honey.utils.ValidationUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.Objects;

public class Product {
    public final int id;
    public final String title;
    public final String description;
    public final String imageUrl;
    public final List<String> types;
    public final List<String> volumes;

    public Product(
        int id,
        String title,
        String imageUrl,
        String description,
        List<String> types,
        List<String> volumes
    ) {
        ValidationUtils.requireTextNotEmpty(title);
        Objects.requireNonNull(imageUrl, "Image url not supplied");
        Objects.requireNonNull(description);

        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.description = description;
        this.types = types;
        this.volumes = volumes;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Product && ((Product) obj).id == id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Product [" + "id = " + id + ", " +
                "title = '" + title + "', " +
                "description = '" + description + "', " +
                "types = [" + Strings.join(types, ',') + "], " +
                "volumes = [" + Strings.join(volumes, ',') + "], ";
    }
}
