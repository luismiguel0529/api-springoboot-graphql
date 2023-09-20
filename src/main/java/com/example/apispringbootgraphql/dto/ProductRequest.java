package com.example.apispringbootgraphql.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String id;
    private String name;
    private double price;
    private int amount;
    private Long categoryId;
}
