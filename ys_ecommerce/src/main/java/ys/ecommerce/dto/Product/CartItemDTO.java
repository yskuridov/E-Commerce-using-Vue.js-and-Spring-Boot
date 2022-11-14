package ys.ecommerce.dto.Product;

import ys.ecommerce.model.Product.CartItem;

public class CartItemDTO {
    String id;
    String amount;
    String product;

    public CartItemDTO(String id, String amount, String product){
        this.id = id;
        this.amount = amount;
        this.product = product;
    }

    public CartItemDTO(CartItem item){
        this(Long.toString(item.getId()), Integer.toString(item.getAmount()), Long.toString(item.getProduct().getId()));
    }
}
