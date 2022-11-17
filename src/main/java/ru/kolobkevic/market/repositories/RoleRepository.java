package ru.kolobkevic.market.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kolobkevic.market.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
