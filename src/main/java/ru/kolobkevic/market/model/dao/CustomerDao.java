package ru.kolobkevic.market.model.dao;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.kolobkevic.market.crud.SessionFactoryUtils;
import ru.kolobkevic.market.model.dto.Customer;
import ru.kolobkevic.market.model.dto.Product;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomerDao {
    public final SessionFactoryUtils factory;

    public List<Product> findProducts(Long id){
        try (Session session = factory.getSession()) {
            session.beginTransaction();
            List<Product> productList = session.get(Customer.class, id).getProductList();
            session.getTransaction().commit();
            return productList;
        }
    }
}
