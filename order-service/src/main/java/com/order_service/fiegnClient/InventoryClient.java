package com.order_service.fiegnClient;

import com.order_service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service", url = "http://localhost:8082")
public interface InventoryClient {

    @GetMapping("/api/inventory")
    List<InventoryResponse> isInStock(@RequestParam List<String> skuCode);
}
