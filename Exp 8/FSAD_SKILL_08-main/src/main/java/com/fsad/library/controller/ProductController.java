package com.fsad.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fsad.library.model.Product;
import com.fsad.library.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductRepository repo;

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        return repo.save(product);
    }

    @GetMapping("/category/{category}")
    public List<Product> getByCategory(@PathVariable String category){
        return repo.findByCategory(category);
    }

    @GetMapping("/filter")
    public List<Product> filter(@RequestParam double min, @RequestParam double max){
        return repo.findByPriceBetween(min, max);
    }

    @GetMapping("/sorted")
    public List<Product> sorted(){
        return repo.sortByPrice();
    }

    @GetMapping("/expensive/{price}")
    public List<Product> expensive(@PathVariable double price){
        return repo.findExpensive(price);
    }
}