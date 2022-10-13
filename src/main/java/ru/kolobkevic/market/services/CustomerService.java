package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.model.dao.CustomerDao;
import ru.kolobkevic.market.model.dto.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerDao customerDao;

    public List<Product> findProducts(Long id){
        return null;
    }
}
