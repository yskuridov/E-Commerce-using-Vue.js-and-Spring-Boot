package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ys.ecommerce.model.User.Customer;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> getCustomerById(Long id);
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.cart cart WHERE c.id = :id")
    Optional<Customer> getCustomerWithCart(Long id);
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders orders WHERE c.id = :id")
    Optional<Customer> getCustomerWithOrders(Long id);
    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.orders orders WHERE c.username = :username")
    Optional<Customer> getCustomerByName(String username);
}
