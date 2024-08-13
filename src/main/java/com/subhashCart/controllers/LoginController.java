package com.subhashCart.controllers;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.models.Customer;
import com.subhashCart.models.UserSession;
import com.subhashCart.services.CustomerService;
import com.subhashCart.services.LoginLogoutService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginLogoutService loginService;

       // Handler to register a new customer

    @PostMapping(value = "/register/customer", consumes = "application/json")
    public ResponseEntity<Customer> registerAccountHandler(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.addCustomer(customer), HttpStatus.CREATED);
    }

    // Handler to login a user

    @PostMapping(value = "/login/customer", consumes = "application/json")
    public ResponseEntity<UserSession> loginCustomerHandler(@Valid @RequestBody CustomerDTO customerdto){
        return new ResponseEntity<>(loginService.loginCustomer(customerdto), HttpStatus.ACCEPTED);
    }



    // Handler to logout a user

    @PostMapping(value = "/logout/customer", consumes = "application/json")
    public ResponseEntity<SessionDTO> logoutCustomerHandler(@RequestBody SessionDTO sessionToken){
        return new ResponseEntity<>(loginService.logoutCustomer(sessionToken), HttpStatus.ACCEPTED);
    }


}
