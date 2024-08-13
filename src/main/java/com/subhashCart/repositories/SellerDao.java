package com.subhashCart.repositories;

import com.subhashCart.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerDao extends JpaRepository<Seller, Integer> {

}
