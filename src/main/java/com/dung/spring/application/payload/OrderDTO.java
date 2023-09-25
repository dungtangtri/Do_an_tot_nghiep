package com.dung.spring.application.payload;

import com.dung.spring.application.models.ShoppingCartModel;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private String orderDescription;
    private List<ShoppingCartModel> cartItems;
    private String customerEmail;
    private String customerName;
}
