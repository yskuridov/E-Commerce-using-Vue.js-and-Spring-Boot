package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ys.ecommerce.model.User.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> getCustomerById(Long id);
}
