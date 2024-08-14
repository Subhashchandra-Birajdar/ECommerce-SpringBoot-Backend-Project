package com.subhashCart.services;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.CustomerUpdateDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.exceptions.CustomerException;
import com.subhashCart.exceptions.CustomerNotFoundException;
import com.subhashCart.models.Address;
import com.subhashCart.models.CreditCard;
import com.subhashCart.models.Customer;
import com.subhashCart.models.Order;

import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws CustomerException;

    public Customer getLoggedInCustomerDetails(String token) throws CustomerNotFoundException;

    Customer getCustomerByMobileNo(String mobileNo, String token) throws CustomerNotFoundException;

    List<Customer> getAllCustomers(String token) throws CustomerNotFoundException;

    Customer updateCustomer(CustomerUpdateDTO customer, String token) throws CustomerNotFoundException;

    public Customer updateCreditCardDetails(String token, CreditCard card) throws CustomerException;
    
    Customer updateCustomerMobileNoOrEmailId(CustomerUpdateDTO customerUpdateDTO, String token) throws CustomerNotFoundException;

    Customer updateCustomerPassword(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;

    SessionDTO deleteCustomer(CustomerDTO customerDTO, String token) throws CustomerNotFoundException;

    Customer updateAddress(Address address, String type, String token) throws CustomerException;

    Customer deleteAddress(String type, String token) throws CustomerException, CustomerNotFoundException;

    public List<Order> getCustomerOrders(String token) throws CustomerException;

}
