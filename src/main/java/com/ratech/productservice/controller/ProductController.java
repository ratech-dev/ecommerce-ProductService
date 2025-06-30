package com.ratech.productservice.controller;

import com.ratech.productservice.dto.CreateProductRequestDto;
import com.ratech.productservice.model.Product;
import com.ratech.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(CreateProductRequestDto requestDto) {

        // validate request

        // create product
        this.productService.createProduct(requestDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = this.productService.getProducts();
        return ResponseEntity.ok().body(products);
    }
}
