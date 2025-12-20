package cloud.matheudcunha.ecommerce.controller.dto;

import cloud.matheudcunha.ecommerce.entity.OrderItemEntity;

import java.math.BigDecimal;

public record OrderItemResponseDto(
        BigDecimal salePrice,
        Integer quantity,
        ProductResponseDto product
) {
    public static OrderItemResponseDto fromEntity(OrderItemEntity entity) {
        return new OrderItemResponseDto(
                entity.getSalePrice(),
                entity.getQuantity(),
                ProductResponseDto.fromEntity(entity.getId().getProduct())
        );
    }
}
