package ys.ecommerce.model.Order;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Products.CartItem;
import ys.ecommerce.model.User.Customer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class CommerceOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Customer buyer;
    @OneToMany
    private List<CartItem> products;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    public CommerceOrder(Customer buyer, LocalDate orderDate, LocalDate deliveryDate){
        this.buyer = buyer;
        this.products = buyer.getCart();
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }
}
