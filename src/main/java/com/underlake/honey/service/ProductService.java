package com.underlake.honey.service;

import com.underlake.honey.dao.ProductEntity;
import com.underlake.honey.domain.Product;
import com.underlake.honey.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts() {
        return productRepository.findAll().stream().map(this::productFromEntity).collect(Collectors.toList());
    }

    private Product productFromEntity(ProductEntity entity) {
        return new Product(
            entity.getId(),
            entity.getTitle(),
            entity.getImageUrl(),
            entity.getDescription(),
            entity.getTypes(),
            entity.getVolumes()
        );
    }

    public Product productFromGraphQLInput(Map<String, Object> input) {
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
        return productFromEntity(entity);
    }

    private ProductEntity entityFromProduct(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setTitle(product.title);
        entity.setDescription(product.description);
        entity.setImageUrl(product.imageUrl);
        entity.setVolumes(product.volumes);
        entity.setTypes(product.types);
        return entity;
    }

    public Product addProduct(Product product) {
        ProductEntity entity = productRepository.save(entityFromProduct(product));
        return productFromEntity(entity);
    }

    public boolean deleteProductById(int id) {
        try {
            ProductEntity entity = productRepository.findById(id).orElseThrow();
            productRepository.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
