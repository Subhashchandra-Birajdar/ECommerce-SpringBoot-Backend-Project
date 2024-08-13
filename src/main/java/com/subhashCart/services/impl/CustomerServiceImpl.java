package com.subhashCart.services.impl;

import com.subhashCart.exceptions.CustomerException;
import com.subhashCart.models.Customer;
import com.subhashCart.repositories.CustomerDao;
import com.subhashCart.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao cDao;

    @Override
    public Customer addCustomer(Customer customer) {

        customer.setCreatedOn(LocalDateTime.now());

        Optional<Customer> existing = cDao.findByMobileNo(customer.getMobileNo());

        if(existing.isPresent())
            throw new CustomerException("Customer already exists. Please try to login with your mobile no");

        cDao.save(customer);

        return customer;
    }

}
