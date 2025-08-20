package com.inventry_service.controller;

import com.inventry_service.dto.InventoryResponse;
import com.inventry_service.entity.Inventory;
import com.inventry_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.property.access.spi.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/inventory")
public class InventryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        log.info("Received inventory check request for skuCode: {}", skuCode);
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory addInventory(@RequestBody Inventory inventoryResponse) {
        log.info("Received request to add inventory: {}", inventoryResponse);
        Inventory inventory = inventoryService.addInventory(inventoryResponse);
        log.info("Added inventory: {}", inventory);
        return inventory;
    }
}
