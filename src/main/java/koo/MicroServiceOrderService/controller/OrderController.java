package koo.MicroServiceOrderService.controller;

import koo.MicroServiceOrderService.dto.OrderDto;
import koo.MicroServiceOrderService.jpa.OrderEntity;
import koo.MicroServiceOrderService.service.OrderService;
import koo.MicroServiceOrderService.vo.RequestOrder;
import koo.MicroServiceOrderService.vo.ResponseOrder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    Environment env;
    OrderService orderService;

    @Autowired
    public OrderController(Environment env, OrderService orderService) {
        this.env = env;
        this. orderService = orderService;
    }

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's working in Order Service on PORT %s", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders/") // 특정 사용자의 주문
    public ResponseEntity<ResponseOrder> createOrder(@RequestBody RequestOrder orderDtails, @PathVariable("userId") String userId) {
        ModelMapper mapper = new ModelMapper(); //  ModelMapper는 DTO를 엔티티로 바꿀때 쉽게 바꿀수 있게 해주는 해준다. (pom.xml에서 의존성을 추가해야한다.)
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // 딱 맞아 떨어지지 않으면 변환을 못하게 전략 설정
        OrderDto orderDto = mapper.map(orderDtails, OrderDto.class);

        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseOrder);
    }

    @GetMapping("/{userId}/orders/") // 특정 사용자의 주문
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) {
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();

        orderList.forEach(o -> {
            result.add(new ModelMapper().map(o, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
