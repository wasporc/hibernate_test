package ru.hiber.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hiber.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
