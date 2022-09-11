package ys.ecommerce.dto.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Review.Comment;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private String id;
    private String customer;
    private String vendor;
    private String comment;

    public CommentDTO(Comment comment){
        this(Long.toString(comment.getId()), Long.toString(comment.getCustomer().getId()), Long.toString(comment.getVendor().getId()), comment.getComment());
    }
}
