package koo.MicroServiceOrderService.service;

import koo.MicroServiceOrderService.dto.OrderDto;
import koo.MicroServiceOrderService.jpa.OrderEntity;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);

}
