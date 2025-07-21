package durden.company.inventory.services;

import durden.company.inventory.DTOs.CartItemDTO;
import durden.company.inventory.entities.InventoryItem;
import durden.company.inventory.repositories.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryItemService {

    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryItemService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public void saveInventoryItem(InventoryItem inventoryItem) {
        inventoryItemRepository.save(inventoryItem);
    }

    public int getTotalQuantityByProductId(Long productId) {
        return inventoryItemRepository.sumQuantityByProductId(productId);
    }

    public List<InventoryItem> getInventoryItemsSortedByQuantity(Long productId) {
        return inventoryItemRepository.findByProductIdOrderByQuantityDesc(productId);
    }

    @Transactional
    public void deductInventoryItems(List<CartItemDTO> cartItems) {
        for (CartItemDTO item : cartItems) {
            int available = getTotalQuantityByProductId(item.getProductId());
            if (available < item.getQuantity()) {
                throw new RuntimeException("Not enough product with ID " + item.getProductId());
            }
        }

        for (CartItemDTO item : cartItems) {
            int remainingToDeduct = item.getQuantity();
            List<InventoryItem> inventoryItems = getInventoryItemsSortedByQuantity(item.getProductId());

            for (InventoryItem inventoryItem : inventoryItems) {
                if (remainingToDeduct <= 0) break;

                int available = inventoryItem.getQuantity();
                int toDeduct = Math.min(available, remainingToDeduct);
                inventoryItem.setQuantity(available - toDeduct);
                remainingToDeduct -= toDeduct;

                inventoryItemRepository.save(inventoryItem);
            }
        }
    }
}
