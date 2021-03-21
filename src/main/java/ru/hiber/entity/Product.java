package ru.hiber.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @OneToMany
    @JoinTable(name = "persons_product",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "persons_id")
    )
    private List<Person> personList;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Integer id ,String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return  "Номер : " + id +
                ", Название : '" + name + '\'' +
                ", Цена : " + price ;
    }
}
