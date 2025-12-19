package cloud.matheudcunha.ecommerce.service;

import cloud.matheudcunha.ecommerce.controller.dto.CreateOrderDto;
import cloud.matheudcunha.ecommerce.controller.dto.OrderItemDto;
import cloud.matheudcunha.ecommerce.controller.dto.OrderSummaryDto;
import cloud.matheudcunha.ecommerce.entity.*;
import cloud.matheudcunha.ecommerce.exception.CreateOrderException;
import cloud.matheudcunha.ecommerce.repository.OrderRepository;
import cloud.matheudcunha.ecommerce.repository.ProductRepository;
import cloud.matheudcunha.ecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    public OrderEntity createOrder(CreateOrderDto dto){


        var order = new OrderEntity();

        // 1. Validate user
        var user = this.validateUser(dto);

        // 2. Validate Order Item
        var orderItems = validateOrderItems(order, dto);

        // 3. Calculate order total
        var total =  calculateOrderTotal(orderItems);

        // 4. Map to Entity
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setItems(orderItems);
        order.setTotal(total);

        // 5. Repository save
        return orderRepository.save(order);
    }



    private List<OrderItemEntity> validateOrderItems(
            OrderEntity order,
            CreateOrderDto dto
    ) {

        if (dto.items().isEmpty()){
            throw new CreateOrderException("order items is empty");
        }
        
        return dto.items()
                .stream()
                .map(orderItemDto -> getOrderItem(order, orderItemDto))
                .toList();
        
    }

    private OrderItemEntity getOrderItem(
            OrderEntity order,
            OrderItemDto orderItemDto
    ) {

        var orderItemEntity = new OrderItemEntity();
        var id = new OrderItemId();

        var product = getProduct(orderItemDto.productId());

        id.setOrder(order);
        id.setProduct(product);

        orderItemEntity.setId(id);
        orderItemEntity.setQuantity(orderItemDto.quantity());
        orderItemEntity.setSalePrice(product.getPrice());


        return orderItemEntity;

    }

    private ProductEntity getProduct(Long productId){
        return productRepository.findById(productId)
                .orElseThrow(() -> new CreateOrderException("product not found"));
    }


    private UserEntity validateUser(CreateOrderDto dto) {

        return userRepository.findById(dto.userId())
                .orElseThrow(() -> new CreateOrderException("user not found"));
    }

    private BigDecimal calculateOrderTotal(List<OrderItemEntity> items) {

        return items
                .stream()
                .map(i -> i.getSalePrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

    }

    public Page<OrderSummaryDto> findAll(Integer page, Integer pageSize) {

        return orderRepository.findAll(PageRequest.of(page, pageSize))
                .map(orderEntity -> {
                    return new OrderSummaryDto(
                            orderEntity.getOrderId(),
                            orderEntity.getOrderDate(),
                            orderEntity.getUser().getId(),
                            orderEntity.getTotal()
                    );
                });
    }
}
