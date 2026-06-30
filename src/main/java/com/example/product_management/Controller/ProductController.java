package com.example.product_management.Controller;

import com.example.product_management.Service.ProductService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
