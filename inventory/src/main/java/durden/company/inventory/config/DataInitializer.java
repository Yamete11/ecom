package durden.company.inventory.config;

import durden.company.inventory.entities.Address;
import durden.company.inventory.entities.InventoryItem;
import durden.company.inventory.entities.Warehouse;
import durden.company.inventory.repositories.WarehouseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final WarehouseRepository warehouseRepository;

    public DataInitializer(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) {
        Warehouse warehouse = new Warehouse();
        warehouse.setTitle("Central Warehouse");

        Address address = new Address();
        address.setCountry("Poland");
        address.setCity("Warsaw");
        address.setStreet("Main Street 1");
        address.setPostCode("00-001");

        warehouse.setAddress(address);

        for (long i = 1; i <= 12; i++) {
            InventoryItem item = new InventoryItem();
            item.setProductId(i);
            item.setQuantity(10);
            item.setWarehouse(warehouse);
            warehouse.getItems().add(item);
        }

        warehouseRepository.save(warehouse);
    }

}
