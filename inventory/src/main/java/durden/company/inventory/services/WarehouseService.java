package durden.company.inventory.services;

import durden.company.inventory.DTOs.CartCheckoutEventDTO;
import durden.company.inventory.entities.Warehouse;
import durden.company.inventory.kafka.InventoryKafkaProducer;
import durden.company.inventory.repositories.WarehouseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final InventoryItemService inventoryItemService;
    private final InventoryKafkaProducer inventoryKafkaProducer;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository, InventoryItemService inventoryItemService, InventoryKafkaProducer inventoryKafkaProducer) {
        this.warehouseRepository = warehouseRepository;
        this.inventoryItemService = inventoryItemService;
        this.inventoryKafkaProducer = inventoryKafkaProducer;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public void postCheckout(CartCheckoutEventDTO cartCheckoutEventDTO) {
        inventoryItemService.deductInventoryItems(cartCheckoutEventDTO.getItems());
        inventoryKafkaProducer.sendCartCheckoutEvent(cartCheckoutEventDTO);
    }

}
