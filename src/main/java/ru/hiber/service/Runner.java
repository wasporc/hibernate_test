package ru.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.hiber.dao.PersonsDao;
import ru.hiber.dao.ProductDao;

@Component
public class Runner  implements CommandLineRunner {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PersonsDao personsDao;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(personsDao.findById(1L));
    }
}
