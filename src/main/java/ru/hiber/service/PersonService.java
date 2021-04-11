package ru.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hiber.dto.PersonCreationDto;
import ru.hiber.entity.Person;
import ru.hiber.repo.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Optional<Person> getPerson(Long id){
        return personRepository.findById(id);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Optional<Person> saveOrUpdate(Person person){
        personRepository.save(person);
        return Optional.of(person);
    }

    public void remove(Long id){
        personRepository.deleteById(id);
    }

    public void saveAll(PersonCreationDto productsCreationDto){
        productsCreationDto.getPersons().forEach(this::saveOrUpdate);
    }

}
