package ys.ecommerce.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Order.Order;
import ys.ecommerce.model.Product.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CUSTOMER")
@Data
@NoArgsConstructor

public class Customer extends CommerceUser {
    @OneToMany
    private List<Product> cart;
    @OneToMany
    private List<Order> orders;
    public Customer(String username, String password, String firstName, String lastName, String address) {
        super(username, password, firstName, lastName, address);
        cart = new ArrayList<>();
        orders = new ArrayList<>();
    }
}
