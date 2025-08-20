package com.order_service.repository;

import com.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Long> {
}
