package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ys.ecommerce.model.Product.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
