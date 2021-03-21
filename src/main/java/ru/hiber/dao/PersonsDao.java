package ru.hiber.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hiber.entity.Person;
import ru.hiber.service.Manager;

import java.util.List;
import java.util.Optional;

@Component
public class PersonsDao implements Dao<Person> {

    @Autowired
    private Manager manager;

    @Override
    public List<Person> findAll(){
        return manager.getEntityManager().createQuery("select p from Person p", Person.class)
                .getResultList();
    }
    @Override
    public Optional<Person> findById(Long id){
        return Optional.ofNullable(manager.getEntityManager().find(Person.class, id));
    }

    @Override
    public void saveOrUpdate(Person entity) {
        if (entity.getId() == null)
            manager.getEntityManager().persist(entity);
        else
            manager.getEntityManager().merge(entity);
    }

    @Override
    public void remove(Long id) {
        Person person = manager.getEntityManager().find(Person.class, id);
        if (person != null){
            manager.getEntityManager().remove(person);
        }
    }

}
