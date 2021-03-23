package ru.hiber.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hiber.entity.Product;
import ru.hiber.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/app")
public class ProductPath {
    private static Logger logger = LoggerFactory.getLogger(ProductPath.class);
    private static Gson gson = new GsonBuilder().create();

    @Autowired
    private ProductService service;

    @GetMapping(value = "/product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getById(@PathVariable Long id){
        Optional<Product> productOptional = service.getById(id);

        if (productOptional.isPresent()){
            Product product = productOptional.get();
            logger.info("product : {}",product.toString());
            logger.info("list size : {}",product.getPersonList().size());
//            String json = gson.toJson(product);
//            logger.info(json);
            return ResponseEntity.ok(product);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
