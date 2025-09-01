package durden.company.cart.repositories;

import durden.company.cart.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    List<CartItem> findByCartId(Long cartId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem c WHERE c.productId = :productId")
    int deleteByProductId(Long productId);
}