package ys.ecommerce.model.Review;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Vendor vendor;
    @Max(value=10)
    private int rating;
    private String comment;
    public Review(Customer customer, Vendor vendor, int rating, String comment){
        this.customer = customer;
        this.vendor = vendor;
        this.rating = rating;
        this.comment = comment;
    }
}
