package cloud.matheudcunha.ecommerce.repository;

import cloud.matheudcunha.ecommerce.entity.BillingAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddressEntity, Long> {
}
