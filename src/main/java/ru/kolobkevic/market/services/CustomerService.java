package ru.kolobkevic.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kolobkevic.market.model.Customer;
import ru.kolobkevic.market.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Optional<Customer> find(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
