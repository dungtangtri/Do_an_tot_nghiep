package com.dung.spring.application.repository;

import com.dung.spring.application.models.ShoppingCartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartModel,Integer> {

}
