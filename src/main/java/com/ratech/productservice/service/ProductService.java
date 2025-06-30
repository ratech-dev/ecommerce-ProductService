package com.ratech.productservice.service;

import com.ratech.productservice.dto.CreateProductRequestDto;
import com.ratech.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
       return products;
    }

    public Product createProduct(CreateProductRequestDto request) {
        return this.createProductInstance(request);
    }

    private Product createProductInstance(CreateProductRequestDto request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        return product;
    }
}
