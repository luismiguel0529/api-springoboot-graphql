package com.example.apispringbootgraphql.repository;

import com.example.apispringbootgraphql.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
