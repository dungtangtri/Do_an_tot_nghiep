package com.dung.spring.application.security.services;

import com.dung.spring.application.models.ProductModel;
import com.dung.spring.application.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductModel> getAllProducts() {
        return this.productRepository.findAll();
    }
     public void addProduct(ProductModel productModel){
         this.productRepository.save(productModel);
     }
    public void deleteProduct(Integer id){
         this.productRepository.deleteById(id);
    }
}