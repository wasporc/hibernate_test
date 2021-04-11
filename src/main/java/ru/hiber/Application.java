package ru.hiber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        ProductDao productDao = new ProductDao();
//        System.out.println(productDao.getById(1));
//        System.out.println(productDao.findAll());
//        productDao.saveOrUpdate(new Product(1, "Green Apple", 1.2));
//        productDao.saveOrUpdate(new Product("Red Apple", 1.5));
        //productDao.deleteById(4);
    }
}
