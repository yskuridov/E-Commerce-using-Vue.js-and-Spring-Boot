package ys.ecommerce.model.Products;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.Vendor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String imageUrl;
    private String description;
    private double price;
    private double deliveryFee;
    private int stock;
    @ManyToOne
    private Vendor vendor;

    public Product(String name, String imageUrl, String description, double price, double deliveryFee, int stock, Vendor vendor){
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.stock = stock;
        this.vendor = vendor;
    }
}
