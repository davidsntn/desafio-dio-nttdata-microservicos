package com.nttdata.challenge.davidsantana.project.orders_service.dto;

import java.util.List;

public class OrderResponse {

    private List<ProductResponse> items;
    private Double totalPrice;

    public OrderResponse() {}

    public OrderResponse(List<ProductResponse> items, Double totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public List<ProductResponse> getItems() {
        return items;
    }

    public void setItems(List<ProductResponse> items) {
        this.items = items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
