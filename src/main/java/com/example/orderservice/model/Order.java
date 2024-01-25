package com.example.orderservice.model;

import lombok.Data;

@Data
public class Order {
    private String product;
    private Long quantity;
}
