package ru.hiber;

import ru.hiber.dao.ProductDao;
import ru.hiber.entity.Product;

public class Application {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        System.out.println(productDao.getById(1));
        System.out.println(productDao.findAll());
        productDao.saveOrUpdate(new Product(1, "Green Apple", 1.2));
        productDao.saveOrUpdate(new Product("Red Apple", 1.5));
        productDao.deleteById(4);

    }
}
