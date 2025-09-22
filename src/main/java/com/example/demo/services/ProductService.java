package com.example.demo.services;

import com.example.demo.responses.ConfigResponse;

public interface ProductService {
    ConfigResponse<Product> getProducts(Integer page, Integer size);

}
