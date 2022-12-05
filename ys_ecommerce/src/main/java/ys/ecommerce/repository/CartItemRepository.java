package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ys.ecommerce.model.Products.CartItem;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCustomerId(Long customerId);
}
