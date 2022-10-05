package ys.ecommerce.model.Order;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Product.CartItem;
import ys.ecommerce.model.Product.Product;
import ys.ecommerce.model.User.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class CommerceOrder {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Customer buyer;
    @OneToMany
    private List<CartItem> products;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    public CommerceOrder(Customer buyer, List<CartItem> products, LocalDate orderDate, LocalDate deliveryDate){
        this.buyer = buyer;
        this.products = products;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }
}
