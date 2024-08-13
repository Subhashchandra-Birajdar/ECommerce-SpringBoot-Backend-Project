package com.subhashCart.repositories;

import com.subhashCart.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {

//	public Map<Product,Integer> findbyName(String productName);
//	public Cart findbyId(Integer cartId);
}