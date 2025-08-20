package com.order_service.event;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OrderEvent extends ApplicationEvent {

    private String orderNumber;

    public OrderEvent(Object source, String orderNumber) {
        super(source);
        this.orderNumber = orderNumber;
    }

    public OrderEvent(String orderNumber) {
        super(orderNumber);
        this.orderNumber = orderNumber;
    }
}
