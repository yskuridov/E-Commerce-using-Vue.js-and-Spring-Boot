package ys.ecommerce.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public CartItem(int amount, Product product){
        this.amount = amount;
        this.product = product;
    }
}
