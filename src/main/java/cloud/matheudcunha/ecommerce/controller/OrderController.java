package cloud.matheudcunha.ecommerce.controller;

import cloud.matheudcunha.ecommerce.controller.dto.CreateOrderDto;
import cloud.matheudcunha.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(name = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDto dto){

        var order = orderService.createOrder(dto);

        return ResponseEntity.created(URI.create("/orders/" + order.getId())).build();
    }

}
