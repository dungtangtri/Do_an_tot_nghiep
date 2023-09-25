package com.dung.spring.application.controllers;

import com.dung.spring.application.models.CustomerModel;
import com.dung.spring.application.models.OrderModel;
import com.dung.spring.application.models.ProductModel;
import com.dung.spring.application.payload.OrderDTO;
import com.dung.spring.application.payload.ResponseOrderDTO;
import com.dung.spring.application.security.services.CustomerService;
import com.dung.spring.application.security.services.OrderService;
import com.dung.spring.application.security.services.ProductService;
import com.dung.spring.application.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;
@RestController
@RequestMapping("/api")

public class ShoppingCartController {


        private OrderService orderService;
        private ProductService productService;
        private CustomerService customerService;


        public ShoppingCartController(OrderService orderService, ProductService productService, CustomerService customerService) {
            this.orderService = orderService;
            this.productService = productService;
            this.customerService = customerService;
        }

        private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

        @GetMapping(value = "/getAllProducts")
        public ResponseEntity<List<ProductModel>> getAllProducts() {

            List<ProductModel> productList = productService.getAllProducts();

            return ResponseEntity.ok(productList);
        }

        @GetMapping(value = "/getOrder/{orderId}")
        public ResponseEntity<OrderModel> getOrderDetails(@PathVariable int orderId) {

            OrderModel order = orderService.getOrderDetail(orderId);
            return ResponseEntity.ok(order);
        }


        @PostMapping("/placeOrder")
        public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
            logger.info("Request Payload " + orderDTO.toString());
            ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
            float amount = orderService.getCartAmount(orderDTO.getCartItems());

            CustomerModel customer = new CustomerModel(orderDTO.getCustomerName(), orderDTO.getCustomerEmail());
            Integer customerIdFromDb = customerService.isCustomerPresent(customer);
            if (customerIdFromDb != null) {
                customer.setId(customerIdFromDb);
                logger.info("Customer already present in db with id : " + customerIdFromDb);
            }else{
                customer = customerService.saveCustomer(customer);
                logger.info("Customer saved.. with id : " + customer.getId());
            }
            OrderModel order = new OrderModel(orderDTO.getOrderDescription(), customer, orderDTO.getCartItems());
            order = orderService.saveOrder(order);
            logger.info("Order processed successfully..");

            responseOrderDTO.setAmount(amount);
            responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
            responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
            responseOrderDTO.setOrderId(order.getId());
            responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

            logger.info("test push..");

            return ResponseEntity.ok(responseOrderDTO);
        }

}
