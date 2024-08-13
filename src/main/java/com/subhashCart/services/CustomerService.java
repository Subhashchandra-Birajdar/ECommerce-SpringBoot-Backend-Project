package com.subhashCart.services;

import com.subhashCart.exceptions.CustomerException;
import com.subhashCart.models.Customer;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws CustomerException;
}
