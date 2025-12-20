package cloud.matheudcunha.ecommerce.controller.dto;

import cloud.matheudcunha.ecommerce.entity.TagEntity;

public record TagResponseDto(
        Long tagId,
        String name

) {
    public static TagResponseDto fromEntity(TagEntity entity) {
        return new TagResponseDto(
                entity.getId(),
                entity.getName()
        );
    }
}
