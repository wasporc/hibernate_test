package ru.hiber.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.hiber.entity.Product;

@Mapper
public interface ProductMapping {
    ProductMapping MAPPER = Mappers.getMapper(ProductMapping.class);

    Product toProduct(ProductDto productDto);

    ProductDto fromProduct(Product product);
}
