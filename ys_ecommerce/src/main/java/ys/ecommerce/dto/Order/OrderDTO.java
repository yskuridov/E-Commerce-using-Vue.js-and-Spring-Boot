package ys.ecommerce.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ys.ecommerce.model.Order.CommerceOrder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String buyer;
    private String orderDate;
    private String deliveryDate;

    public OrderDTO(CommerceOrder order){
        this(Long.toString(order.getId()), Long.toString(order.getBuyer().getId()),  order.getOrderDate().toString(), order.getDeliveryDate().toString());
    }
}
