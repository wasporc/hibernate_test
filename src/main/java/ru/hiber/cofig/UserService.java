package ru.hiber.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hiber.entity.Person;
import ru.hiber.repo.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> personOptional = personRepository.findByLogin(s);
        if (personOptional.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь с таким логин не найден");
        }
        Person person = personOptional.get();
        return new User(
                person.getLogin(),
                person.getPassword(),
                List.of(new SimpleGrantedAuthority(person.getRole().getName())));
    }
}
