package koo.MicroServiceOrderService.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDto implements Serializable {

    private String productId;
    private Integer qty; // 수량
    private Integer unitPrice;
    private Integer totalPrice;

    // 주문시 정보
    private String orderId;
    private String userId;

}
