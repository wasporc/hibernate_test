package ru.hiber.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hiber.entity.Role;
import ru.hiber.repo.RoleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> getRole(Long id){
        return roleRepository.findById(id);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Optional<Role> saveOrUpdate(Role role){
        roleRepository.save(role);
        return Optional.of(role);
    }

    public void remove(Role role){
        roleRepository.delete(role);
    }
}
