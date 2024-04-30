package org.menekseyuncu.storemanagementsystem.cart.repository;

import org.menekseyuncu.storemanagementsystem.cart.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {

    CartItemEntity findByCartIdAndProductId(Long cartId, Long productId);

}
