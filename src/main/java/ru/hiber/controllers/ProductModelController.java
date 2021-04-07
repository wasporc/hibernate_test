package ru.hiber.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.hiber.dto.ProductsCreationDto;
import ru.hiber.entity.Product;
import ru.hiber.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductModelController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public String showAll(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/allProduct";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model){
        productService.remove(id);
        model.addAttribute("products", productService.findAll());
        return "redirect:/products/all";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        ProductsCreationDto productForm = new ProductsCreationDto();
        //for (int i = 1; i <= 3; i++) {
            productForm.addProduct(new Product());
       // }
        model.addAttribute("form", productForm);
        return "products/createProductForm";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute ProductsCreationDto form, Model model) {
        productService.saveAll(form);
        model.addAttribute("products", productService.findAll());
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        List<Product> list = new ArrayList<>();
        list.add(productService.getById(id).orElse(new Product()));
        model.addAttribute("form", new ProductsCreationDto(list));
        return "products/editProductForm";
    }

}
