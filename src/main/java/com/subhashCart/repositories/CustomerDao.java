package com.subhashCart.repositories;

import com.subhashCart.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByMobileNo(String mobileNo);

    Optional<Customer> findByEmailId(String emailId);

}
