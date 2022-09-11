package ys.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ys.ecommerce.model.Review.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByVendorId(long id);
    List<Comment> findAllByCustomerId(long id);

}
