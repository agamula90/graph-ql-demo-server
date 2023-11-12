package com.underlake.honey.controller;

import com.underlake.honey.dao.ContentEntity;
import com.underlake.honey.dao.ProductEntity;
import com.underlake.honey.dto.Product;
import com.underlake.honey.dto.SupplementaryItem;
import com.underlake.honey.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public List<Product> products() {
        return repository.findProducts();
    }

    @MutationMapping
    Product addProduct(@Arguments Map<String, Object> product) {
        ProductEntity entity = repository.save(ProductEntity.fromGraphQLInput(product));
        return new Product(entity);
    }
}
