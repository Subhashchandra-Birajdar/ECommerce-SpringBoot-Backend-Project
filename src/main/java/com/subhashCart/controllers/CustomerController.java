package com.subhashCart.controllers;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.CustomerUpdateDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.models.Address;
import com.subhashCart.models.CreditCard;
import com.subhashCart.models.Customer;
import com.subhashCart.models.Order;
import com.subhashCart.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Handler to get a list of all customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomersHandler(
            @RequestHeader("token") String token){
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(token), HttpStatus.ACCEPTED);
    }


    // Handler to Get a customer details of currently logged in user - sends data as per token
    @GetMapping("/customer/current")
    public ResponseEntity<Customer> getLoggedInCustomerDetailsHandler(
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.getLoggedInCustomerDetails(token), HttpStatus.ACCEPTED);
    }

    // Handler to Get a customer details by his mobile id
    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomerByMobileNoHandler(
            @RequestParam("mobile") String mobileNo,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.getCustomerByMobileNo(mobileNo, token), HttpStatus.ACCEPTED);
    }


    // Handler to Update a customer
    @PutMapping("/customer")
    public ResponseEntity<Customer> updateCustomerHandler(
            @Valid @RequestBody CustomerUpdateDTO customerUpdate,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.updateCustomer(customerUpdate, token), HttpStatus.ACCEPTED);
    }


    // Handler to update a customer email-id or mobile no
    @PutMapping("/customer/update")
    public ResponseEntity<Customer> updateCustomerMobileEmailHandler(
            @Valid @RequestBody CustomerUpdateDTO customerUpdate,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.updateCustomerMobileNoOrEmailId(customerUpdate, token), HttpStatus.ACCEPTED);
    }


    // Handler to update customer password
    @PutMapping("/customer/update/password")
    public ResponseEntity<Customer> updateCustomerPasswordHandler(
            @Valid @RequestBody CustomerDTO customerDto,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.updateCustomerPassword(customerDto, token), HttpStatus.ACCEPTED);
    }

    // Handler to Add or update new customer Address
    @PutMapping("/customer/update/address")
    public ResponseEntity<Customer> updateAddressHandler(
            @Valid @RequestBody Address address,
            @RequestParam("type") String type,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.updateAddress(address, type, token), HttpStatus.ACCEPTED);
    }


    // Handler to Remove a user address
    @DeleteMapping("/customer/delete/address")
    public ResponseEntity<Customer> deleteAddressHandler(
            @RequestParam("type") String type,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.deleteAddress(type, token), HttpStatus.ACCEPTED);
    }

    // handler to logout a customer
    @DeleteMapping("/customer")
    public ResponseEntity<SessionDTO> deleteCustomerHandler(
            @Valid @RequestBody CustomerDTO customerDto,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.deleteCustomer(customerDto, token), HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/orders")
    public ResponseEntity<List<Order>> getCustomerOrdersHandler(
            @RequestHeader("token") String token){
        return new ResponseEntity<>(customerService.getCustomerOrders(token), HttpStatus.ACCEPTED);
    }

    // Handler to update Credit card details
    @PutMapping("/customer/update/card")
    public ResponseEntity<Customer> updateCreditCardHandler(
            @RequestHeader("token") String token,
            @Valid @RequestBody CreditCard newCard){
        return new ResponseEntity<>(customerService.updateCreditCardDetails(token, newCard), HttpStatus.ACCEPTED);
    }


}
