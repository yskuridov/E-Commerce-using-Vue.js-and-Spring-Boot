package ys.ecommerce.model.Review;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Vendor vendor;
    private String comment;

    public Comment(Customer writtenBy, Vendor writtenTo, String comment){
        this.customer = writtenBy;
        this.vendor = writtenTo;
        this.comment = comment;
    }
}
