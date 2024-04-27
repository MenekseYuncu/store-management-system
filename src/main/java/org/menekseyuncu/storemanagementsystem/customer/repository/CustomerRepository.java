package org.menekseyuncu.storemanagementsystem.customer.repository;

import org.menekseyuncu.storemanagementsystem.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    boolean existsByEmail(String email);

}
