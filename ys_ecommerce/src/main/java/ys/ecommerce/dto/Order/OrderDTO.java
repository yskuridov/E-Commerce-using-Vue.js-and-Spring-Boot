package ys.ecommerce.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Order.Order;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String buyer;
    private String seller;
    private String orderDate;
    private String deliveryDate;

    public OrderDTO(Order order){
        this(Long.toString(order.getId()), Long.toString(order.getBuyer().getId()), Long.toString(order.getSeller().getId()), order.getOrderDate().toString(), order.getDeliveryDate().toString());
    }
}
