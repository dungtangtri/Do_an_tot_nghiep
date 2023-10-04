package com.dung.spring.application.payload;

import lombok.Data;

@Data
public class ProductDTO {
    String name;
    Integer availableQuantity;
    Integer price;

}
