package cloud.matheudcunha.ecommerce.repository;

import cloud.matheudcunha.ecommerce.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
