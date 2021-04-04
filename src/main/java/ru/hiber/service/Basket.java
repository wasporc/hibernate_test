package ru.hiber.service;

import org.springframework.stereotype.Service;
import ru.hiber.dto.ProductDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Basket {
    private Map<Long, List<ProductDto>> productBasket;

    public Basket() {
        productBasket = new HashMap<>();
    }

    public void add(ProductDto product, Long id){
        List<ProductDto> list = productBasket.get(id);
        if (list != null){
            list.add(product);
        }else {
            ArrayList<ProductDto> arrayList = new ArrayList<>();
            arrayList.add(product);
            productBasket.put(id, arrayList);
        }
    }

    public List<ProductDto> getList(Long id){
        return productBasket.get(id);
    }

    public void remove(ProductDto product, Long id){
        List<ProductDto> list = productBasket.get(id);
        if (list != null){
            list.remove(product);
        }
    }
}
