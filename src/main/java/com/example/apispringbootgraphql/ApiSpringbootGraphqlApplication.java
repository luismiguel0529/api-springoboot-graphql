package com.example.apispringbootgraphql;

import com.example.apispringbootgraphql.entities.Category;
import com.example.apispringbootgraphql.entities.Product;
import com.example.apispringbootgraphql.repository.CategoryRepository;
import com.example.apispringbootgraphql.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class ApiSpringbootGraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSpringbootGraphqlApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CategoryRepository categoryRepository, ProductRepository productRepository){
		Random random = new Random();
		return args -> {
			List.of("Computadoras", "Impresoras", "Smartphones").forEach(cat -> {
				Category category = Category.builder().name(cat).build();
				categoryRepository.save(category);
			});

			categoryRepository.findAll().forEach(category -> {
				for (int i = 0; i < 10; i++) {
					Product product = Product.builder()
							.id(UUID.randomUUID().toString())
							.name(category.getName() + i)
							.price( 100 + Math.random()*500)
							.amount(random.nextInt(100))
							.category(category)
							.build();
					productRepository.save(product);
				}
			});
		};
	}

}
