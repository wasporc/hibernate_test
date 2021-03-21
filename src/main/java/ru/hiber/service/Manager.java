package ru.hiber.service;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Repository
public class Manager {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    private void init(){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }
}
