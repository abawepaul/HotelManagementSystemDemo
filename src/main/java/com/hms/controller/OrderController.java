package com.hms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.exception.ResourceNotFoundException;
import com.hms.model.Order;
import com.hms.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    // Get All Notes
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/orders")
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }

    // Get a Single Note
    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable(value = "id") Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
    }

    // Update a Note
    @PutMapping("/orders/{id}")
    public Order updateOrder(@PathVariable(value = "id") Long orderId,
                                            @Valid @RequestBody Order orderDetails) {

    	Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

    	order.setCustomerName(orderDetails.getCustomerName());
    	order.setOrderItems(orderDetails.getOrderItems());

        Order updatedOrder = orderRepository.save(order);
        return updatedOrder;
    }

    // Delete a Note
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") Long orderId) {
    	Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

    	orderRepository.delete(order);

        return ResponseEntity.ok().build();
    }
    
}