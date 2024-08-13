package com.subhashCart.services;

import com.subhashCart.exceptions.SellerException;
import com.subhashCart.models.Seller;

import java.util.List;

public interface SellerService {

    public Seller addSeller(Seller seller);

    public List<Seller> getAllSellers() throws SellerException;

    public Seller getSellerById(Integer sellerId)throws SellerException;

    public Seller updateSeller(Seller seller)throws SellerException;

    public Seller updateSellerMobile(Integer sellerId, String mobile)throws SellerException;

    public Seller deleteSellerById(Integer sellerId)throws SellerException;

}
