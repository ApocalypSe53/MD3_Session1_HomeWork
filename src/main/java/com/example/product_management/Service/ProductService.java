package com.example.product_management.Service;

import com.example.product_management.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Laptop Gaming", 1500.0));
        products.add(new Product(2L, "Bàn phím cơ", 100.0));
        products.add(new Product(3L, "Chuột không dây", 50.0));
    }

    public List<Product> getAllProducts() {
        return products;
    }

}
