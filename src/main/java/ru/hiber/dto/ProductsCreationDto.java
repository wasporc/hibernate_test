package ru.hiber.dto;

import ru.hiber.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsCreationDto {
    private List<Product> products;

    public ProductsCreationDto(List<Product> products) {
        this.products = products;
    }

    public ProductsCreationDto() {
        this(new ArrayList<>());
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
