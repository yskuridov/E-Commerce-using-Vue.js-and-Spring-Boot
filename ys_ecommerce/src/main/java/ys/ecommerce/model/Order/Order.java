package ys.ecommerce.model.Order;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Product.Product;
import ys.ecommerce.model.User.Customer;
import ys.ecommerce.model.User.Vendor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Customer buyer;
    @ManyToOne
    private Vendor seller;
    //@OneToMany
    //List<Product> products;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

}
