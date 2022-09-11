package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ys.ecommerce.model.Review.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByVendorId(Long id);
}
