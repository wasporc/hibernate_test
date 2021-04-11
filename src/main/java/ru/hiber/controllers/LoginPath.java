package ru.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.hiber.dto.ProductDto;
import ru.hiber.entity.Person;
import ru.hiber.repo.PersonRepository;
import ru.hiber.repo.ProductRepository;
import ru.hiber.service.AuthenticationFacade;
import ru.hiber.service.Basket;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginPath {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private Basket basket;

    @Autowired
    private AuthenticationFacade facade;

    @GetMapping
    public String index(Model model) {
        Optional<Person> byLogin = personRepository.findByLogin(facade.getAuthentication().getName());
        if (byLogin.isPresent()) {
            List<ProductDto> list = basket.getList(byLogin.get().getId());
            if (list != null){
                model.addAttribute("count", "Корзина (" + list.size() + ")");
            }else{
                model.addAttribute("count", "Корзина (0)");
            }
        }
        model.addAttribute("products", productRepository.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }
}
