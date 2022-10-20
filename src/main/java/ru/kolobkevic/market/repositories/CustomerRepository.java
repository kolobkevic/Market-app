package ru.kolobkevic.market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolobkevic.market.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
