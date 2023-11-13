package com.underlake.honey.controller;

import com.underlake.honey.domain.DeleteResult;
import com.underlake.honey.domain.Product;
import com.underlake.honey.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.Arguments;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    List<Product> products() {
        return productService.findProducts();
    }

    @MutationMapping
    Product addProduct(@Arguments Map<String, Object> product) {
        return productService.addProduct(productService.productFromGraphQLInput(product));
    }

    @MutationMapping
    DeleteResult deleteProduct(@Argument int id) {
        boolean isDeleteSucceeded = productService.deleteProductById(id);
        return isDeleteSucceeded ? DeleteResult.ok() : DeleteResult.fail();
    }
}
