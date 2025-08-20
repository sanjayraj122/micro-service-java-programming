package com.product_service.controller;

import com.product_service.dto.ProductRequest;
import com.product_service.dto.ProductResponse;
import com.product_service.entity.Product;
import com.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveProduct(@RequestBody ProductRequest product){
        productService.createProduct(product);
        return "product create successfully";
    }

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts(){
      return productService.getAllProducts();

    }

}
