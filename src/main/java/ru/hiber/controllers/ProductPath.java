package ru.hiber.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hiber.entity.Product;
import ru.hiber.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class ProductPath {
    private static Logger logger = LoggerFactory.getLogger(ProductPath.class);
    private static Gson gson = new GsonBuilder().create();

    @Autowired
    private ProductService service;

    @GetMapping(
            value = "/products/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable Long id){
        Optional<Product> productOptional = service.getById(id);
        if (productOptional.isPresent()){
            Product product = productOptional.get();
            logger.info("product : {}",product.toString());
            return ResponseEntity.ok(gson.toJson(product));
        }
        else{
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(String.format("not found product with id %s", id));
        }
    }

    @GetMapping(
            value = "/products",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll(){
        List<Product> list = service.findAll();
        return ResponseEntity.ok(gson.toJson(list));
    }

    //{"name":"orange","price":2.5}
    @PostMapping(
            value = "/products",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        logger.info("post body product {}", product);
        product = service.add(product);
        return ResponseEntity.ok().body(gson.toJson(product));
    }

    @GetMapping(
            value = "/products/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            service.remove(id);
            logger.info(String.format("delete ok with id %s", id));
            return ResponseEntity.ok().build();
        }catch (RuntimeException e){
            logger.info(String.format("delete bad request id %s", id));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
