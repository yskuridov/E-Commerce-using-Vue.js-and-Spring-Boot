package ys.ecommerce.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Review.Review;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private String id;
    private String customer;
    private String customerName;
    private String vendor;
    private String rating;
    private String comment;

    public ReviewDTO(Review review){
        this(Long.toString(review.getId()), Long.toString(review.getCustomer().getId()), review.getCustomer().getUsername() , Long.toString(review.getVendor().getId()), Integer.toString(review.getRating()), review.getComment());
    }
}
