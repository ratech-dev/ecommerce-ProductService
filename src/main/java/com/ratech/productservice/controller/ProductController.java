package com.ratech.productservice.controller;

import com.ratech.productservice.dto.CreateProductRequestDto;
import com.ratech.productservice.dto.User;
import com.ratech.productservice.model.Product;
import com.ratech.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private RestTemplate restTemplate;

    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestHeader("authToken") String token, @RequestBody CreateProductRequestDto requestDto) {

        // validate request

        // validate Token
        ResponseEntity<User> response = restTemplate.getForEntity("http://localhost:8080/validate/token/" + token, User.class);
        User user = response.getBody();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!user.getRoles().contains("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

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
