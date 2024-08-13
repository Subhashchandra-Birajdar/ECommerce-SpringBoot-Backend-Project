package com.subhashCart.services;

import com.subhashCart.dtos.CustomerDTO;
import com.subhashCart.dtos.SellerDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.models.UserSession;

public interface LoginLogoutService {

    public UserSession loginCustomer(CustomerDTO customer);

    public SessionDTO logoutCustomer(SessionDTO session);

    public void checkTokenStatus(String token);

    public void deleteExpiredTokens();

    public UserSession loginSeller(SellerDTO seller);

    public SessionDTO logoutSeller(SessionDTO session);
}
