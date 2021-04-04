package ru.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hiber.entity.Product;
import ru.hiber.service.ProductService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Controller
@ApiIgnore
public class ProductModelController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String index(Model model) {
        List<Product> all = productService.findAll();
        model.addAttribute("hello", "it's work" );
        model.addAttribute("products", all);
        return "index";
    }

    @PostMapping
    @RequestMapping(value = "/products")
    public String delete(@RequestParam Long id){
        productService.remove(id);
        return "redirect:";
    }

}
