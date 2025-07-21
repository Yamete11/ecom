package durden.company.inventory.repositories;

import durden.company.inventory.entities.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    @Query("SELECT COALESCE(SUM(i.quantity), 0) FROM InventoryItem i WHERE i.productId = :productId")
    int sumQuantityByProductId(@Param("productId") Long productId);

    List<InventoryItem> findByProductIdOrderByQuantityDesc(Long productId);
}

