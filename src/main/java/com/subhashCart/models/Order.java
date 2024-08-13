package com.subhashCart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subhashCart.models.enums.OrderStatusValues;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    @PastOrPresent
    private LocalDate date;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatusValues orderStatus;

    private Double total;

    private String cardNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customerId")
    private Customer customer;

    @OneToMany
    private List<CartItem> ordercartItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
}
