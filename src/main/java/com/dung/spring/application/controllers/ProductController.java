package com.dung.spring.application.controllers;

import com.dung.spring.application.models.ProductModel;
import com.dung.spring.application.payload.ProductDTO;
import com.dung.spring.application.security.services.ProductService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    ModelMapper modelMapper = new ModelMapper();
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/getAllProducts")
    public ResponseEntity<List<ProductModel>> getAllProducts() {

        List<ProductModel> productList = productService.getAllProducts();

        return ResponseEntity.ok(productList);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addProduct")
    public ResponseEntity<ProductDTO> addProduct(
            @Valid @RequestBody ProductDTO productDTO
    ){
        ProductModel product = modelMapper.map(productDTO, ProductModel.class);
        productService.addProduct(product);
        logger.info("Admin vừa thêm sản phẩm" + productDTO);
        return ResponseEntity.ok().body(productDTO);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(
            @Valid @PathVariable Integer id
    ){
        productService.deleteProduct(id);
        logger.info("Admin vừa xóa sản phẩm id:" + id);
        return ResponseEntity.ok().body("Admin vừa xóa sản phẩm id:" + id);
    }


}
