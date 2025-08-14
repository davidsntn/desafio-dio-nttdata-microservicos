package com.nttdata.challenge.davidsantana.project.orders_service.controller;

import com.nttdata.challenge.davidsantana.project.orders_service.dto.OrderRequest;
import com.nttdata.challenge.davidsantana.project.orders_service.dto.OrderResponse;
import com.nttdata.challenge.davidsantana.project.orders_service.service.OrderServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @NotEmpty @RequestBody List<OrderRequest> orderRequests) {

        OrderResponse orderResponse = orderService.createOrder(orderRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
