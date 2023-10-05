package com.dung.spring.application.controllers;
import com.dung.spring.application.models.OrderModel;
import com.dung.spring.application.payload.OrderDTO;
import com.dung.spring.application.payload.ResponseOrderDTO;
import com.dung.spring.application.security.services.OrderService;
import com.dung.spring.application.security.services.ProductService;
import com.dung.spring.application.security.services.UserDetailsImpl;
import com.dung.spring.application.util.DateUtil;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
@RestController
@RequestMapping("/api")

public class ShoppingCartController {
        @Autowired
        private OrderService orderService;
        @Autowired
        private ProductService productService;
        public ShoppingCartController() {

        }
        private static final Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @GetMapping(value = "/getOrder/{orderId}")
        public ResponseEntity<OrderModel> getOrderDetails(@Valid @PathVariable int orderId) {

            OrderModel order = orderService.getOrderDetail(orderId);
            return ResponseEntity.ok(order);
        }


        @PostMapping("/placeOrder")
        public ResponseEntity<ResponseOrderDTO> placeOrder(
                @AuthenticationPrincipal UserDetailsImpl user,
                @NotNull @RequestBody OrderDTO orderDTO) {
            ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
            float amount = orderService.getCartAmount(orderDTO.getCartItems());
            if(user != null){
                LocalDateTime create_time = LocalDateTime.now();
                OrderModel newOrder = new OrderModel(orderDTO.getOrderDescription(), user.getUsername(), orderDTO.getCustomerEmail(),orderDTO.getCustomerName(),orderDTO.getCartItems(), create_time);
                orderService.saveOrder(newOrder);
                logger.info("Order processed successfully.");
                responseOrderDTO.setAmount(amount);
                responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
                responseOrderDTO.setOrderId(newOrder.getId());
                responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());
            }
            return ResponseEntity.ok(responseOrderDTO);
        }

}
