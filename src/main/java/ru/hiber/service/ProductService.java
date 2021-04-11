package ru.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hiber.dto.ProductMapping;
import ru.hiber.dto.ProductsCreationDto;
import ru.hiber.entity.Product;
import ru.hiber.repo.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getById(Long id){
        return productRepository.findById(id);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product add(Product product){
        return productRepository.save(product);
    }

    public void remove(Long id) throws RuntimeException{
        try {
            productRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("can't delete");
        }
    }

    public void saveAll(ProductsCreationDto productsCreationDto){
        productsCreationDto.getProducts()
                .stream()
                .map(ProductMapping.MAPPER::toProduct)
                .forEach(this::add);
    }

}
