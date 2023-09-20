package com.example.apispringbootgraphql.controller;

import com.example.apispringbootgraphql.entities.Category;
import com.example.apispringbootgraphql.entities.Product;
import com.example.apispringbootgraphql.repository.CategoryRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryGraphqlController {

    final private CategoryRepository categoryRepository;

    public CategoryGraphqlController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }


    @QueryMapping
    public Category getCategoryById(@Argument Long id){
        return categoryRepository.findById(id)
                .orElseThrow( () -> new RuntimeException(String.format("Category %s not found", id)));
    }

}
