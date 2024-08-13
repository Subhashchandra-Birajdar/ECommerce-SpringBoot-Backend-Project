package com.subhashCart.services;

import com.subhashCart.dtos.SellerDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.exceptions.SellerException;
import com.subhashCart.models.Seller;

import java.util.List;

public interface SellerService {

    public Seller addSeller(Seller seller);

    public List<Seller> getAllSellers() throws SellerException;

    public Seller getSellerById(Integer sellerId)throws SellerException;

    public Seller getSellerByMobile(String mobile, String token) throws SellerException;

    public Seller getCurrentlyLoggedInSeller(String token) throws SellerException;

    public SessionDTO updateSellerPassword(SellerDTO sellerDTO, String token) throws SellerException;

    public Seller updateSeller(Seller seller, String token)throws SellerException;

    public Seller updateSellerMobile(SellerDTO sellerdto, String token)throws SellerException;

    public Seller deleteSellerById(Integer sellerId, String token)throws SellerException;

}
