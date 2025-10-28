package com.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookshop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
