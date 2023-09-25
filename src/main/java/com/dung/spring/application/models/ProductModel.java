package com.dung.spring.application.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table
public class ProductModel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int availableQuantity;
    private Long price;
}
