package com.dung.spring.application.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Order_Detail")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String orderDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCartModel.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ShoppingCartModel> cartItems;


    public OrderModel(String orderDescription, User user , List<ShoppingCartModel> cartItems){
        this.orderDescription = orderDescription;
        this.user = user;
        this.cartItems = cartItems;
    }
}
