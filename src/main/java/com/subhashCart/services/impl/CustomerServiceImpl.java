package com.subhashCart.services.impl;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.CustomerUpdateDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.exceptions.CustomerException;
import com.subhashCart.exceptions.CustomerNotFoundException;
import com.subhashCart.exceptions.LoginException;
import com.subhashCart.models.Address;
import com.subhashCart.models.Customer;
import com.subhashCart.models.Order;
import com.subhashCart.models.UserSession;
import com.subhashCart.repositories.CustomerDao;
import com.subhashCart.repositories.SessionDao;
import com.subhashCart.services.CustomerService;
import com.subhashCart.services.LoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{

    @Autowired
    private CustomerDao customerDao;

    // ctrl+alt+shift+j -->select same word
    // alt ->  right side file move in intellij

    @Autowired
    private LoginLogoutService loginService;

    @Autowired
    private SessionDao sessionDao;

    @Override
    public Customer addCustomer(Customer customer) {

        customer.setCreatedOn(LocalDateTime.now());

        Optional<Customer> existing = customerDao.findByMobileNo(customer.getMobileNo());

        if(existing.isPresent())
            throw new CustomerException("Customer already exists. Please try to login with your mobile no");
         customerDao.save(customer);

        return customer;
    }

    // Method to get a customer by mobile number

    @Override
    public Customer getCustomerByMobileNo(String mobileNo, String token){

        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);


        Optional<Customer> opt = customerDao.findByMobileNo(mobileNo);

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer not registered with given mobile number or email-id");

        return opt.get();
    }


    // Method to get all customers - only selller or admin can get all customers - check validity of seller token

    @Override
    public List<Customer> getAllCustomers(String token) throws CustomerNotFoundException {

        // update to seller

        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        List<Customer> customers = customerDao.findAll();

        if(customers.size() == 0)
            throw new CustomerNotFoundException("No record exists");

        return customers;
    }


    // Method to update entire customer details - either mobile number or mobile number should be correct

    @Override
    public Customer updateCustomer(CustomerUpdateDTO customer, String token) throws CustomerNotFoundException {

        System.out.println(customer);

        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        Optional<Customer> opt = customerDao.findByMobileNo(customer.getMobileNo());

        Optional<Customer> res = customerDao.findByEmailId(customer.getEmailId());

        if(opt.isEmpty() && res.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist with given mobile no or email-id");

        Customer existingCustomer = null;

        if(opt.isPresent())
            existingCustomer = opt.get();
        else
            existingCustomer = res.get();

        UserSession user = sessionDao.findByToken(token).get();

        if(existingCustomer.getCustomerId() == user.getUserId()) {

            if(customer.getFirstName() != null) {
                existingCustomer.setFirstName(customer.getFirstName());
            }

            if(customer.getLastName() != null) {
                existingCustomer.setLastName(customer.getLastName());
            }

            if(customer.getEmailId() != null) {
                existingCustomer.setEmailId(customer.getEmailId());
            }

            if(customer.getMobileNo() != null) {
                existingCustomer.setMobileNo(customer.getMobileNo());
            }

            if(customer.getPassword() != null) {
                existingCustomer.setPassword(customer.getPassword());
            }

            if(customer.getAddress() != null) {
                for(Map.Entry<String, Address> values : customer.getAddress().entrySet()) {
                    existingCustomer.getAddress().put(values.getKey(), values.getValue());
                }
            }

            customerDao.save(existingCustomer);
            return existingCustomer;

        }
        else {
            throw new CustomerException("Error in updating. Verification failed.");
        }


    }


    // Method to update customer mobile number - customer should send valid email-id

    @Override
    public Customer updateCustomerMobileNoOrEmailId(CustomerUpdateDTO customerUpdateDTO, String token) throws CustomerNotFoundException {

        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        Optional<Customer> opt = customerDao.findByEmailId(customerUpdateDTO.getEmailId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist with given email-id");

        Customer existingCustomer = opt.get();

        UserSession user = sessionDao.findByToken(token).get();

        if(user.getUserId() == existingCustomer.getCustomerId()) {

            existingCustomer.setMobileNo(customerUpdateDTO.getMobileNo());

            customerDao.save(existingCustomer);

            return existingCustomer;
        }
        else {
            throw new CustomerException("Error in updating. Verification failed.");
        }
    }

    // Method to update password
    @Override
    public Customer updateCustomerPassword(CustomerDTO customerDTO, String token) throws CustomerNotFoundException {
        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        Optional<Customer> opt = customerDao.findByMobileNoOrEmailId(customerDTO.getMobileId(), customerDTO.getEmailId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer not registered with given mobile number");

        Customer existingCustomer = opt.get();

        UserSession user = sessionDao.findByToken(token).get();

        if(user.getUserId() == existingCustomer.getCustomerId()) {

            existingCustomer.setPassword(customerDTO.getPassword());

            return customerDao.save(existingCustomer);
        }
        else {
            throw new CustomerException("Error in updating password. Verification failed.");
        }

    }
    // Method to delete a customer by mobile id
    @Override
    public SessionDTO deleteCustomer(CustomerDTO customerDTO, String token) throws CustomerNotFoundException {
        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        Optional<Customer> opt = customerDao.findByMobileNo(customerDTO.getMobileId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer Not exist with given mobile no");

        Customer deletedCustomer = opt.get();

        UserSession user = sessionDao.findByToken(token).get();

        SessionDTO session = new SessionDTO();

        session.setMessage("");

        session.setToken(token);

        if(user.getUserId() == deletedCustomer.getCustomerId()) {

            customerDao.delete(deletedCustomer);

            session.setMessage("Account Deleted and Logged out");

            return loginService.logoutCustomer(session);

        }
        else {
            throw new CustomerException("Error in deleting. Verification failed.");
        }

    }

    @Override
    public Customer updateAddress(Address address, String type, String token) throws CustomerException {
        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

        existingCustomer.getAddress().put(type, address);

        return customerDao.save(existingCustomer);
    }

    @Override
    public Customer deleteAddress(String type, String token) throws CustomerException, CustomerNotFoundException {
        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

        if(existingCustomer.getAddress().containsKey(type) == false)
            throw new CustomerException("Address type does not exist");

        existingCustomer.getAddress().remove(type);

        return customerDao.save(existingCustomer);
    }

    @Override
    public List<Order> getCustomerOrders(String token) throws CustomerException {

        if(token.contains("customer") == false) {
            throw new LoginException("Invalid session token for customer");
        }

        loginService.checkTokenStatus(token);

        UserSession user = sessionDao.findByToken(token).get();

        Optional<Customer> opt = customerDao.findById(user.getUserId());

        if(opt.isEmpty())
            throw new CustomerNotFoundException("Customer does not exist");

        Customer existingCustomer = opt.get();

        List<Order> myOrders = existingCustomer.getOrders();

        if(myOrders.size() == 0)
            throw new CustomerException("No orders found");

        return myOrders;
    }


}






