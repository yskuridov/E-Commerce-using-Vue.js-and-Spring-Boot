package ys.ecommerce.model.User;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("VENDOR")
@Data
@NoArgsConstructor
public class Vendor extends User {
    public Vendor(String username, String password, String firstName, String lastName, String address){
        super(username, password, firstName, lastName, address);
        int rating;
        //List<Review> reviews
        boolean hasOpenComments;
    }
}
