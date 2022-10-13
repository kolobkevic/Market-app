package ru.kolobkevic.market.model.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.kolobkevic.market.crud.SessionFactoryUtils;
import ru.kolobkevic.market.model.dto.Customer;
import ru.kolobkevic.market.model.dto.Product;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductDao {
    public final SessionFactoryUtils factory;

    public Product findById(Long id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Product> findAll() {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("select p from Product p ORDER BY p.id").getResultList();
            Hibernate.initialize(productList);
            session.getTransaction().commit();
            return productList;
        }
    }

    public void deleteById(Long id) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            session.createQuery("delete from Product where id= :id").setParameter("id", id).executeUpdate();
            session.getTransaction().commit();
        }
    }

    public Product saveOrUpdate(Product product) {
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            return product;
        }
    }

    public List<Customer> findCustomers(Long id){
        try (Session session = factory.getSession()) {
            session.beginTransaction();
//            List<Customer> customersList = session.get(Product.class, id).getCustomerList();
//            session.getTransaction().commit();
            var result = session.get(Product.class, id);
            Hibernate.initialize(result.getCustomerList());
            return result.getCustomerList();
        }
    }
}
