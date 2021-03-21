package ru.hiber.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    void saveOrUpdate(T entity);
    void remove(Long id);
}
