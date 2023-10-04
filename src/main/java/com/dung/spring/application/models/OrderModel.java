package com.dung.spring.application.models;

import com.dung.spring.application.util.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "username")
    private String username;

    private String email;
    private String customerName;
    @Column(name = "creation_time")
    private LocalDateTime create_time;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCartModel.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ShoppingCartModel> cartItems;


    public OrderModel(String orderDescription, String username, String email, String customerName, List<ShoppingCartModel> cartItems, LocalDateTime create_time) {
        this.orderDescription = orderDescription;
        this.username = username;
        this.email = email;
        this.customerName = customerName;
        this.cartItems = cartItems;
        this.create_time = create_time;
    }
}
