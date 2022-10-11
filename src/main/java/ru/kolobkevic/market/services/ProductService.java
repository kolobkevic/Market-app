package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.model.dao.ProductDao;
import ru.kolobkevic.market.model.dto.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
//    private final ProductRepository productRepository;
    private final ProductDao productRepository;

    public Product getProduct(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void saveOrUpdateProduct(Product product){
        productRepository.saveOrUpdate(product);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}