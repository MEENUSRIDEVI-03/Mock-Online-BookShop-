package com.onlinebookshop.service;

import com.onlinebookshop.model.*;
import com.onlinebookshop.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final BookRepository bookRepo;
    private final UserRepository userRepo;
    private final OrderItemRepository itemRepo;

    public OrderService(OrderRepository orderRepo, BookRepository bookRepo, UserRepository userRepo, OrderItemRepository itemRepo) {
        this.orderRepo = orderRepo;
        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    public Order placeOrder(Long userId, List<OrderItem> items) {
        User user = userRepo.findById(userId).orElseThrow();
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");

        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : items) {
            Book book = bookRepo.findById(item.getBook().getBookId()).orElseThrow();
            if (book.getStockQuantity() < item.getQuantity())
                throw new RuntimeException("Not enough stock for book: " + book.getTitle());

            item.setOrder(order);
            item.setBook(book);
            item.setSubtotal(book.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
            total = total.add(item.getSubtotal());

            book.setStockQuantity(book.getStockQuantity() - item.getQuantity());
            bookRepo.save(book);
        }

        order.setTotalAmount(total);
        orderRepo.save(order);
        itemRepo.saveAll(items);
        order.setStatus("Completed");
        return orderRepo.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findByUserUserId(userId);
    }

    public Order getOrder(Long id) {
        return orderRepo.findById(id).orElse(null);
    }
}
