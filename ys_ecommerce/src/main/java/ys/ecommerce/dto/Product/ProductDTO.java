package ys.ecommerce.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Product.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private String price;
    private String deliveryFee;
    private String stock;
    private String soldBy;

    public ProductDTO(Product product){
        this(Long.toString(product.getId()), product.getName(), product.getDescription(), Double.toString(product.getPrice()), Double.toString(product.getDeliveryFee()), Integer.toString(product.getStock()), Long.toString(product.getSoldBy().getId()));
    }
}
