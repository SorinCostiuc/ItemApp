package com.example.itemapp.service;

import org.springframework.stereotype.Service;

@Service
public class ValueCalculatorService {

    public int calculateValue(int price, int quantity){
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity can't be negative");
        }
        return price * quantity;
    }
}
