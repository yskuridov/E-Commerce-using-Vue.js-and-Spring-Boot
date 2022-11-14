package ys.ecommerce.dto.Product;

public class CartItemDTO {
    String id;
    String amount;
    String product;

    public CartItemDTO(String id, String amount, String product){
        this.id = id;
        this.amount = amount;
        this.product = product;
    }
}
