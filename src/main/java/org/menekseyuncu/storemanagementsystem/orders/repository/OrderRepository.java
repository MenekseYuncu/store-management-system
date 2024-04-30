package org.menekseyuncu.storemanagementsystem.orders.repository;

import org.menekseyuncu.storemanagementsystem.orders.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
