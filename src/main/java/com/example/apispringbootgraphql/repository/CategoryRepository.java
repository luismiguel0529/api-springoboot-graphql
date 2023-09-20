package com.example.apispringbootgraphql.repository;

import com.example.apispringbootgraphql.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
