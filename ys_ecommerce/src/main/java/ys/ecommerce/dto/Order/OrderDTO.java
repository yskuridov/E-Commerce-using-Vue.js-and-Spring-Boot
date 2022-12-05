package ys.ecommerce.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.dto.Product.CartItemDTO;
import ys.ecommerce.model.Order.CommerceOrder;
import ys.ecommerce.model.Products.CartItem;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String buyer;
    private List<CartItemDTO> items;
    private String orderDate;
    private String deliveryDate;


    public OrderDTO(CommerceOrder order){
        this.id = Long.toString(order.getId());
        this.buyer = Long.toString(order.getBuyer().getId());
        this.items = getItemsDTO(order.getBuyer().getCart());
        this.orderDate = order.getOrderDate().toString();
        this.deliveryDate = order.getDeliveryDate().toString();
    }

    private List<CartItemDTO> getItemsDTO(List<CartItem> items){
        List<CartItemDTO> dtos = new ArrayList<>();
        for(CartItem item : items){
            dtos.add(new CartItemDTO(item));
        }
        return dtos;
    }

}
