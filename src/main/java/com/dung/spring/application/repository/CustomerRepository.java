package com.dung.spring.application.repository;

import com.dung.spring.application.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {


    public CustomerModel getCustomerByEmailAndName(String email, String name);
}

