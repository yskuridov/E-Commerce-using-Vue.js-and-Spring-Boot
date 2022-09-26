package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ys.ecommerce.model.Order.CommerceOrder;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<CommerceOrder, Long> {
    List<CommerceOrder> findAllByBuyerId(Long id);
}
