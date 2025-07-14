package durden.company.inventory.config;

import durden.company.inventory.entities.Address;
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
        Warehouse warehouse1 = new Warehouse();
        warehouse1.setTitle("Central Warehouse");

        Address address1 = new Address();
        address1.setCountry("Poland");
        address1.setCity("Warsaw");
        address1.setStreet("Main Street 1");
        address1.setPostCode("00-001");

        warehouse1.setAddress(address1);

        Warehouse warehouse2 = new Warehouse();
        warehouse2.setTitle("West Warehouse");

        Address address2 = new Address();
        address2.setCountry("Germany");
        address2.setCity("Berlin");
        address2.setStreet("Westend Avenue 5");
        address2.setPostCode("10115");

        warehouse2.setAddress(address2);

        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);
    }
}
