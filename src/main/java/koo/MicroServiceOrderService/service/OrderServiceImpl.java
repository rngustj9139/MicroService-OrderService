package koo.MicroServiceOrderService.service;

import koo.MicroServiceOrderService.dto.OrderDto;
import koo.MicroServiceOrderService.jpa.OrderEntity;

import javax.persistence.criteria.Order;

public class OrderServiceImpl implements OrderService {

    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
        return null;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return null;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return null;
    }

}
