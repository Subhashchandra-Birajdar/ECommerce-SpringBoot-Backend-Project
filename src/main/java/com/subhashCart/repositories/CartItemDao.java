package com.subhashCart.repositories;

import com.subhashCart.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {

}
