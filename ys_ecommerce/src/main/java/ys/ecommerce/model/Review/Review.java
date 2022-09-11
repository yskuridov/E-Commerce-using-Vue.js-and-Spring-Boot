package ys.ecommerce.model.Review;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;

@Entity
@Data
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Vendor vendor;
    @Max(value=10)
    private int rating;

    public Review(Customer customer, Vendor vendor, int rating){
        this.customer = customer;
        this.vendor = vendor;
        this.rating = rating;
    }
}
