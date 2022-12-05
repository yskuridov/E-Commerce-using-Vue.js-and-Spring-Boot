package ys.ecommerce.model.User;


import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Products.Product;
import ys.ecommerce.model.Review.Comment;
import ys.ecommerce.model.Review.Review;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("VENDOR")
@Data
@NoArgsConstructor
public class Vendor extends CommerceUser {
    @OneToMany
    private List<Review> reviews;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Product> inventory;
    private boolean hasOpenComments;
    public Vendor(String username, String password, String firstName, String lastName, String address){
        super(username, password, firstName, lastName, address);
        double rating;
        reviews = new ArrayList<>();
        hasOpenComments = true;
    }
}
