package cloud.matheudcunha.ecommerce.repository;

import cloud.matheudcunha.ecommerce.entity.OrderItemEntity;
import cloud.matheudcunha.ecommerce.entity.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}
