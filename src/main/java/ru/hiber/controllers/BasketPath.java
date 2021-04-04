package ru.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hiber.dto.ProductDto;
import ru.hiber.dto.ProductMapping;
import ru.hiber.entity.Product;
import ru.hiber.service.Basket;
import ru.hiber.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class BasketPath {

    @Autowired
    private Basket basket;

    @Autowired
    private ProductService service;

    @GetMapping("/basket/{id}")
    public List<ProductDto> getPersonBasket(@PathVariable Long id){
        return basket.getList(id);
    }

    @PostMapping("/basket")
    public void addProduct(@RequestBody Product product, @RequestHeader(name = "ID-USER") String user){
        Optional<Product> byId = service.getById(product.getId());
        byId.ifPresent(value -> basket.add(ProductMapping.MAPPER.fromProduct(value), Long.valueOf(user)));
    }

    @DeleteMapping("/basket")
    public void removeFromBasket(@RequestBody Product product, @RequestHeader(name = "ID-USER") String user){
        Optional<Product> byId = service.getById(product.getId());
        byId.ifPresent(value -> basket.remove(ProductMapping.MAPPER.fromProduct(value), Long.valueOf(user)));
    }
}
