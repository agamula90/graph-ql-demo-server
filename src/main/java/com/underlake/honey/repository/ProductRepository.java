package com.underlake.honey.repository;

import com.underlake.honey.dao.ProductEntity;
import com.underlake.honey.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

    default List<Product> findProducts() {
        return findAll().stream().map(Product::new).collect(Collectors.toList());
    }
}
