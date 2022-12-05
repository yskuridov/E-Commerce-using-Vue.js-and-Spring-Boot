package ys.ecommerce.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Products.CartItem;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTO {
    String id;
    String amount;
    String product;
    String customer;

    public CartItemDTO(CartItem item){
        this(Long.toString(item.getId()), Integer.toString(item.getAmount()), Long.toString(item.getProduct().getId()), Long.toString(item.getCustomer().getId()));
    }
}
