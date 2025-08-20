package com.order_service.service;

import  com.order_service.dto.InventoryResponse;
import com.order_service.dto.OrderLineItemsDto;
import com.order_service.dto.OrderRequest;
import com.order_service.entity.OrderLineItems;
import com.order_service.entity.Orders;
import com.order_service.fiegnClient.InventoryClient;
import com.order_service.lisenter.OrderPlacedEventListener;
import com.order_service.repository.OrderRepo;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderPlacedEventListener orderPlacedEventListener;
    private final InventoryClient inventoryClient;
    private final ObservationRegistry observationRegistry;

    public void placeOrder(OrderRequest orderRequest) {
        Orders order = new Orders();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItems = Optional.ofNullable(orderRequest.getOrderLineItemsDtoList())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItems(orderLineItems);
        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        List<InventoryResponse> inventoryResponses = Observation.createNotStarted("inventory-service-lookup", this.observationRegistry)
                .lowCardinalityKeyValue("call", "inventory-service")
                .observe(() -> inventoryClient.isInStock(skuCodes));

        boolean allProductsInStock = inventoryResponses.stream()
                .allMatch(InventoryResponse::isInStock);
        if (allProductsInStock) {
            orderRepo.save(order);
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }

}
