package com.onlinebookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.onlinebookshop.model.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUserId(Long userId);
}
