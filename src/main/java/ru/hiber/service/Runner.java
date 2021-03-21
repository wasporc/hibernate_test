package ru.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.hiber.dao.Dao;
import ru.hiber.entity.Person;
import ru.hiber.entity.Product;

import java.util.Optional;

@Component
public class Runner  implements CommandLineRunner {

    @Autowired
    private Dao<Product> productDao;

    @Autowired
    private Dao<Person> personsDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(personsDao.findById(1L).get());
        Optional<Product> byId = productDao.findById(1L);
        byId.ifPresent(product -> System.out.println(product.getPersonList()));
    }
}
