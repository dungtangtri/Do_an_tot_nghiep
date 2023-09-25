package com.dung.spring.application.security.services;

import com.dung.spring.application.models.CustomerModel;
import com.dung.spring.application.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel saveCustomer(CustomerModel customer) {
        return customerRepository.save(customer);
    }

    public Integer isCustomerPresent(CustomerModel customer) {
        CustomerModel customer1 = customerRepository.getCustomerByEmailAndName(customer.getEmail(), customer.getName());
        return customer1 != null ? customer1.getId() : null;
    }
}