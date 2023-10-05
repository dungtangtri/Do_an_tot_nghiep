package com.dung.spring.application.payload;

import lombok.Data;

@Data
public class ResponseOrderDTO
{
    private float amount;
    private String date;
    private String OrderDescription;
    private int orderId;
}
