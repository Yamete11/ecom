package durden.company.inventory.controllers;

import durden.company.inventory.DTOs.CartCheckoutEventDTO;
import durden.company.inventory.entities.Warehouse;
import durden.company.inventory.services.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<Void> postCheckout(@RequestBody CartCheckoutEventDTO cartCheckoutEventDTO) {
        warehouseService.postCheckout(cartCheckoutEventDTO);
        return ResponseEntity.noContent().build();
    }

}
