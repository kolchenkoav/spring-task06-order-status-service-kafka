package com.example.orderstatusservice.model;

import lombok.Data;

@Data
public class Order {
    private String product;
    private Long quantity;
}
