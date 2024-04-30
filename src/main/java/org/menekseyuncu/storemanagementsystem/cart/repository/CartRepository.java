package org.menekseyuncu.storemanagementsystem.cart.repository;

import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByCustomerId(Long customerId);
}
