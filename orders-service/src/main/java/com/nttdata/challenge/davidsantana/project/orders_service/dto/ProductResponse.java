package com.nttdata.challenge.davidsantana.project.orders_service.dto;

public class ProductResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final Double price;
    private final Integer quantity;
    private final Double totalPrice;

    private ProductResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.price = builder.price;
        this.quantity = builder.quantity;
        this.totalPrice = builder.totalPrice;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public Integer getQuantity() { return quantity; }
    public Double getTotalPrice() { return totalPrice; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Double price;
        private Integer quantity;
        private Double totalPrice;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder price(Double price) { this.price = price; return this; }
        public Builder quantity(Integer quantity) { this.quantity = quantity; return this; }
        public Builder totalPrice(Double totalPrice) { this.totalPrice = totalPrice; return this; }

        public ProductResponse build() { return new ProductResponse(this); }
    }
}