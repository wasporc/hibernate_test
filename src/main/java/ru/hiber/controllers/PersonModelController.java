package ru.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hiber.dto.PersonCreationDto;
import ru.hiber.entity.Person;
import ru.hiber.service.PersonService;
import ru.hiber.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonModelController {

    @Autowired
    private PersonService service;

    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public String showAll(Model model){
        model.addAttribute("persons", service.getAll());
        return "persons/allPersons";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        PersonCreationDto form = new PersonCreationDto();
        form.add(new Person());
        model.addAttribute("form", form);
        return "persons/createPersonsForm";
    }

    @PostMapping("/save")
    public String savePersons(@ModelAttribute PersonCreationDto personForm, Model model){
        personForm.getPersons()
                .forEach(person -> {
                    roleService.getRole(person.getRole_id()).ifPresent(person::setRole);
                    if (person.getPassword() != null && !person.getPassword().contains("noop"))
                        person.setPassword("{noop}" + person.getPassword());
                });
        service.saveAll(personForm);
        model.addAttribute("persons", service.getAll());
        return "redirect:/persons/all";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        List<Person> list = new ArrayList<>();
        list.add(service.getPerson(id).orElse(new Person()));
        model.addAttribute("form", new PersonCreationDto(list));
        return "persons/editPersonForm";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        service.remove(id);
        model.addAttribute("persons", service.getAll());
        return "redirect:/persons/allPersons";
    }

}
