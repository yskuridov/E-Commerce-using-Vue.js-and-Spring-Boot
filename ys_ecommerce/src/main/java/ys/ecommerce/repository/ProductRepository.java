package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ys.ecommerce.model.Product.Product;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductById(Long id);
    List<Product> findAllByDescriptionContaining(String keyword);
    List<Product> findAllByNameContaining(String name);
    List<Product> findAllByDeliveryFeeIsLessThanEqual(Double price);
    List<Product> findAllByVendorId(Long id);
    List<Product> findAllByPriceIsLessThanEqual(Double price);
}
