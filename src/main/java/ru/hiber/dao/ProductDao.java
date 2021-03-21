package ru.hiber.dao;

import org.springframework.stereotype.Repository;
import ru.hiber.entity.Product;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class ProductDao {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void init(){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Product> findAll(){
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    public Product findById(Integer id){
        return entityManager.find(Product.class, id);
    }

}
