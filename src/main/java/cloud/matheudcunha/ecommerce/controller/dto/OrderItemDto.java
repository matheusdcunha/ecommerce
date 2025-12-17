package cloud.matheudcunha.ecommerce.controller.dto;

public record OrderItemDto(
        Integer quantity,
        Long productId
        ) {
}
