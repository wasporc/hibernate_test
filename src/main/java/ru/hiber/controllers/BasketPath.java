package ru.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hiber.dto.ProductDto;
import ru.hiber.dto.ProductMapping;
import ru.hiber.dto.ProductsCreationDto;
import ru.hiber.entity.Person;
import ru.hiber.entity.Product;
import ru.hiber.repo.PersonRepository;
import ru.hiber.service.AuthenticationFacade;
import ru.hiber.service.Basket;
import ru.hiber.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/basket")
public class BasketPath {

    @Autowired
    private Basket basket;

    @Autowired
    private ProductService service;

    @Autowired
    private AuthenticationFacade facade;
    @Autowired
    private PersonRepository personRepository;

//    @GetMapping("/get/{id}")
//    public List<ProductDto> getPersonBasket(@PathVariable Long id){
//        return basket.getList(id);
//    }

    @GetMapping("/add/{id}")
    public String addProduct(@PathVariable Long id, Model model){
        Optional<Product> byId = service.getById(id);
        Optional<Person> byLogin = personRepository.findByLogin(facade.getAuthentication().getName());
        if (byId.isPresent()){
            if (byLogin.isPresent()){
                basket.add(ProductMapping.MAPPER.fromProduct(byId.get()), byLogin.get().getId());
                int size = basket.getList(byLogin.get().getId()).size();
                model.addAttribute("count", "Корзина (" + size + ")");
            }
        }
        model.addAttribute("products", service.findAll());
        return "redirect:/";
        //byId.ifPresent(product -> byLogin.ifPresent(person -> basket.add(ProductMapping.MAPPER.fromProduct(product), person.getId())));

    }

    @GetMapping("/edit")
    public String editBasket(Model model){
        Optional<Person> byLogin = personRepository.findByLogin(facade.getAuthentication().getName());
        if (byLogin.isPresent()){
            List<ProductDto> productList = basket.getList(byLogin.get().getId());
            if (productList != null){
                List<ProductDto> list = new ArrayList<>(productList);
                model.addAttribute("form", new ProductsCreationDto(list));
            }else {
                model.addAttribute("form", new ProductsCreationDto());
            }
        }else{
            model.addAttribute("form", new ProductsCreationDto());
        }
        return "basket/editBasketForm";
    }

//    @DeleteMapping("/basket")
//    public void removeFromBasket(@RequestBody Product product, @RequestHeader(name = "ID-USER") String user){
//        Optional<Product> byId = service.getById(product.getId());
//        byId.ifPresent(value -> basket.remove(ProductMapping.MAPPER.fromProduct(value), Long.valueOf(user)));
//    }
}
