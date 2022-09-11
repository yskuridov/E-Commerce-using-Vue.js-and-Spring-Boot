package ys.ecommerce.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.User.Vendor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private double price;
    private double deliveryFee;
    private int stock;
    @ManyToOne
    private Vendor vendor;

    public Product(String name, String description, double price, double deliveryFee, int stock, Vendor vendor){
        this.name = name;
        this.description = description;
        this.price = price;
        this.deliveryFee = deliveryFee;
        this.stock = stock;
        this.vendor = vendor;
    }
}
