package com.subhashCart.services;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.CustomerUpdateDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.exceptions.CustomerException;
import com.subhashCart.exceptions.CustomerNotFoundException;
import com.subhashCart.models.Customer;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws CustomerException;

    public Customer getCustomerByMobileNo(String mobileNo, String token) throws CustomerNotFoundException;

    public List<Customer> getAllCustomers(String token) throws CustomerNotFoundException;

    public Customer updateCustomer(CustomerUpdateDTO customer, String token) throws CustomerNotFoundException;

    public Customer updateCustomerMobileNoOrEmailId(CustomerUpdateDTO customerUpdateDTO, String token) throws CustomerNotFoundException;

    public Customer updateCustomerPassword(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;

    public SessionDTO deleteCustomer(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;
}
