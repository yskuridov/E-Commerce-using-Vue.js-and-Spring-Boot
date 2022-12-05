package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ys.ecommerce.model.Order.CommerceOrder;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<CommerceOrder, Long> {
    @Query("SELECT o FROM CommerceOrder o LEFT JOIN FETCH o.products WHERE o.id = :id")
    List<CommerceOrder> findAllByBuyerId(Long id);
}
