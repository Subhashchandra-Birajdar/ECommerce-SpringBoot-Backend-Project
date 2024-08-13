package com.subhashCart.services.impl;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.exceptions.CustomerException;
import com.subhashCart.exceptions.LoginException;
import com.subhashCart.models.Customer;
import com.subhashCart.models.UserSession;
import com.subhashCart.repositories.CustomerDao;
import com.subhashCart.repositories.SessionDao;
import com.subhashCart.services.LoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService {


    @Autowired
    private SessionDao sDao;

    @Autowired
    private CustomerDao cDao;



    // Method to login a customer

    @Override
    public UserSession loginCustomer(CustomerDTO loginCustomer) {

        Optional<Customer> res = cDao.findByMobileNo(loginCustomer.getMobileId());

        if(res.isEmpty())
            throw new CustomerException("Customer record does not exist with given mobile number");

        Customer existingCustomer = res.get();

        Optional<UserSession> opt = sDao.findByUserId(existingCustomer.getCustomerId());

        if(opt.isPresent())
            throw new LoginException("User already logged in");


        if(existingCustomer.getPassword().equals(loginCustomer.getPassword())) {

            UserSession newSession = new UserSession();

            newSession.setUserId(existingCustomer.getCustomerId());
            newSession.setUserType("customer");
            newSession.setSessionStartTime(LocalDateTime.now());
            newSession.setSessionEndTime(LocalDateTime.now().plusHours(1));

            UUID uuid = UUID.randomUUID();
            String token = "customer_" + uuid.toString().split("-")[0];

            newSession.setToken(token);

            return sDao.save(newSession);
        }
        else {
            throw new LoginException("Password Incorrect. Try again.");
        }
    }


    // Method to logout a customer

    @Override
    public SessionDTO logoutCustomer(SessionDTO sessionToken) {

        String token = sessionToken.getToken();

        checkTokenStatus(token);

        Optional<UserSession> opt = sDao.findByToken(token);

        if(!opt.isPresent())
            throw new LoginException("User not logged in. Invalid session token. Login Again.");

        UserSession session = opt.get();

        sDao.delete(session);

        sessionToken.setMessage("Logged out sucessfully.");

        return sessionToken;
    }



    // Method to check status of session token


    @Override
    public void checkTokenStatus(String token) {
        Optional<UserSession> opt = sDao.findByToken(token);

        if(opt.isPresent()) {
            UserSession session = opt.get();
            LocalDateTime endTime = session.getSessionEndTime();
            if(endTime.isBefore(LocalDateTime.now())) {
                sDao.delete(session);
                throw new LoginException("Session expired. Login Again");
            }
        }

    }
    
}
