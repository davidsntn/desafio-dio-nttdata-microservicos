package com.nttdata.challenge.davidsantana.project.orders_service.service;

import com.nttdata.challenge.davidsantana.project.orders_service.client.ProductServiceClient;
import com.nttdata.challenge.davidsantana.project.orders_service.dto.OrderRequest;
import com.nttdata.challenge.davidsantana.project.orders_service.dto.OrderResponse;
import com.nttdata.challenge.davidsantana.project.orders_service.dto.ProductDTO;
import com.nttdata.challenge.davidsantana.project.orders_service.dto.ProductResponse;
import com.nttdata.challenge.davidsantana.project.orders_service.exception.ProductNotFoundException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    @Autowired
    private ProductServiceClient productServiceClient;

    public OrderResponse createOrder(List<OrderRequest> orderItems) {
        List<ProductResponse> detailedItems = orderItems.stream()
                .map(item -> {
                    try {
                        ProductDTO product = productServiceClient
                                .getProductById(item.getProductId())
                                .getBody();
                        if (product == null) {
                            throw new ProductNotFoundException("Produto com ID " + item.getProductId() + " não encontrado");
                        }
                        double itemTotalValue = product.getPrice() * item.getQuantity();

                        return ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .quantity(item.getQuantity())
                                .totalPrice(itemTotalValue)
                                .build();
                    } catch (FeignException.NotFound e) {
                        throw new ProductNotFoundException("Produto com ID " + item.getProductId() + " não encontrado");
                    }
                })
                .collect(Collectors.toList());

        double totalValue = detailedItems.stream()
                .mapToDouble(ProductResponse::getTotalPrice)
                .sum();

        return new OrderResponse(detailedItems, totalValue);
    }
}
