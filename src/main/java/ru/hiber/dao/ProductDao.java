package ru.hiber.dao;

import org.hibernate.cfg.Configuration;
import ru.hiber.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private static final EntityManagerFactory factory;
    static {
        factory = new Configuration()
                .configure("hibernate.xml")
                .buildSessionFactory();
    }
    private static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public List<Product> findAll(){
        EntityManager em = ProductDao.getEntityManager();
        return em.createQuery("select p from Product p", Product.class).getResultList();
    }

    // @Override
    public Product getById(Integer id) {
        EntityManager em = ProductDao.getEntityManager();
        Product product = em.createQuery("select p from Product p where p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        return product;
    }

    public Product saveOrUpdate(Product product){
        EntityManager em = ProductDao.getEntityManager();
        em.getTransaction().begin();
        if(product.getId() != null){
            em.merge(product);
        }else{
            em.persist(product);
        }
        em.getTransaction().commit();
        return product;
    }

    public void deleteById(Integer id){
        EntityManager em = ProductDao.getEntityManager();
        Product product = em.find(Product.class, id);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();
    }

}
