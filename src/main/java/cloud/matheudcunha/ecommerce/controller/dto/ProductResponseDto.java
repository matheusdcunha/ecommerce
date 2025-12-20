package cloud.matheudcunha.ecommerce.controller.dto;

import cloud.matheudcunha.ecommerce.entity.ProductEntity;
import cloud.matheudcunha.ecommerce.entity.TagEntity;

import java.util.List;

public record ProductResponseDto(
        Long productId,
        String productName,
        List<TagResponseDto> tags
) {
    public static ProductResponseDto fromEntity(ProductEntity entity) {
        return new ProductResponseDto(
                entity.getId(),
                entity.getProductName(),
                getTags(entity.getTags())
        );
    }

    private static List<TagResponseDto> getTags(List<TagEntity> tags) {
        return tags.stream()
                .map(TagResponseDto::fromEntity)
                .toList();
    }
}
