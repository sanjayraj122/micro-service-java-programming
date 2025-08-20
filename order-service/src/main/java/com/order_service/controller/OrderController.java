package com.order_service.controller;

import com.order_service.dto.OrderRequest;
import com.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventoryService",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventoryService")
    @Retry(name = "inventoryService", fallbackMethod = "fallbackMethod")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return CompletableFuture.supplyAsync(()->"order placed successfully");
    }

    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {
        return CompletableFuture.supplyAsync(()->"Inventory service is down, please try again later");
    }
}
