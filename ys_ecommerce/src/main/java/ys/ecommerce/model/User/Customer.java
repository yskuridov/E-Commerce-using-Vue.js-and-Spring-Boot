package ys.ecommerce.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
@Data
@NoArgsConstructor

public class Customer extends User{

    public Customer(String username, String password, String firstName, String lastName, String address) {
        super(username, password, firstName, lastName, address);
        //List<Product> cart;
        //List<Order> orders;


    }
}
