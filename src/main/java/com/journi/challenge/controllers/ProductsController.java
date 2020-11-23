package com.journi.challenge.controllers;

import com.journi.challenge.models.ProductCurrency;
import com.journi.challenge.repositories.ProductsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class ProductsController {

    @Inject
    private ProductsRepository productsRepository;

    @GetMapping("/products")
    public List<ProductCurrency> list(@RequestParam(name = "countryCode", defaultValue = "AT") String countryCode) {
        return productsRepository.getAllProducts(countryCode);
    }
}
