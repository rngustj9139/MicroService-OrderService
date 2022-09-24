package koo.MicroServiceOrderService.vo;

import lombok.Data;

@Data
public class RequestOrder {

    private String productId;
    private Integer qty; // 수량
    private Integer unitPrice;

}
