package ru.hiber.dao;

import org.springframework.stereotype.Repository;
import ru.hiber.entity.Person;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
public class PersonsDao {
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @PostConstruct
    public void init(){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Person> findAll(){
        return entityManager.createQuery("select p from Person p", Person.class)
                .getResultList();
    }

    public Person findById(Long id){
        return entityManager.find(Person.class, id);
    }

}
