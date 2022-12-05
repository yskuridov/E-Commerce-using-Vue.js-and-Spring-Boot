package ys.ecommerce.model.Products;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Order.CommerceOrder;
import ys.ecommerce.model.User.Customer;

import javax.persistence.*;
import javax.persistence.criteria.Order;

@Entity
@Data
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int amount;
    @OneToOne
    private Product product;
    @ManyToOne
    private Customer customer;

    public CartItem(int amount, Product product, Customer customer){
        this.amount = amount;
        this.product = product;
        this.customer = customer;
    }
}
