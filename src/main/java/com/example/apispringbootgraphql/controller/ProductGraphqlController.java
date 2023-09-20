package com.example.apispringbootgraphql.controller;

import com.example.apispringbootgraphql.dto.ProductRequest;
import com.example.apispringbootgraphql.entities.Category;
import com.example.apispringbootgraphql.entities.Product;
import com.example.apispringbootgraphql.repository.CategoryRepository;
import com.example.apispringbootgraphql.repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProductGraphqlController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductGraphqlController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @QueryMapping
    public List<Product> productList(){
        return productRepository.findAll();
    }

    @QueryMapping
    public Product getProductById(@Argument String id){
        return productRepository.findById(id)
                .orElseThrow( () -> new RuntimeException(String.format("Product %s not found", id)));
    }

    @MutationMapping
    public Product saveProduct(@Argument ProductRequest productRequest){
        Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
        Product productBD = new Product();
        productBD.setId(UUID.randomUUID().toString());
        productBD.setName(productRequest.getName());
        productBD.setPrice(productRequest.getPrice());
        productBD.setAmount(productRequest.getAmount());
        productBD.setCategory(category);
        return productRepository.save(productBD);
    }

    @MutationMapping
    public Product updateProduct(@Argument String id,@Argument ProductRequest productRequest){
        Category category = categoryRepository.findById(productRequest.getCategoryId()).orElse(null);
        Product productBD = new Product();
        productBD.setId(id);
        productBD.setName(productRequest.getName());
        productBD.setPrice(productRequest.getPrice());
        productBD.setAmount(productRequest.getAmount());
        productBD.setCategory(category);
        return productRepository.save(productBD);
    }

    @MutationMapping
    public String deleteProduct(@Argument String id){
        productRepository.deleteById(id);
        return "DELETE";
    }
}
