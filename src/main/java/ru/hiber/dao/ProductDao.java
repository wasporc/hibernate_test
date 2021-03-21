package ru.hiber.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hiber.entity.Product;
import ru.hiber.service.Manager;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDao implements Dao<Product>{

    @Autowired
    private Manager manager;

    @Override
    public List<Product> findAll(){
        return manager.getEntityManager().createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(manager.getEntityManager().find(Product.class, id));
    }

    @Override
    public void saveOrUpdate(Product entity) {

    }

    @Override
    public void remove(Long id) {

    }


}
