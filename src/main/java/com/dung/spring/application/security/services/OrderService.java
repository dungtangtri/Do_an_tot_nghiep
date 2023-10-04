package com.dung.spring.application.security.services;

import com.dung.spring.application.models.OrderModel;
import com.dung.spring.application.models.ProductModel;
import com.dung.spring.application.models.ShoppingCartModel;
import com.dung.spring.application.repository.OrderRepository;
import com.dung.spring.application.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderModel getOrderDetail(int orderId) {
        Optional<OrderModel> order = this.orderRepository.findById(orderId);
        return order.orElse(null);
    }

    public float getCartAmount(List<ShoppingCartModel> shoppingCartList) {

        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for (ShoppingCartModel cart : shoppingCartList) {

            int productId = cart.getProductId();
            Optional<ProductModel> product = productRepository.findById(productId);
            if (product.isPresent()) {
                ProductModel product1 = product.get();
                if (product1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = product1.getPrice() * product1.getAvailableQuantity();
                    cart.setQuantity(product1.getAvailableQuantity());
                } else {
                    singleCartAmount = cart.getQuantity() * product1.getPrice();
                    availableQuantity = product1.getAvailableQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productRepository.save(product1);
            }
        }
        return totalCartAmount;
    }

    public void saveOrder(OrderModel order) {
        orderRepository.save(order);
    }
}