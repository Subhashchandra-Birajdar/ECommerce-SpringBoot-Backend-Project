package com.subhashCart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subhashCart.models.enums.CategoryEnum;
import com.subhashCart.models.enums.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;

    @NotNull
    @Size(min = 3, max = 30, message = "Product name size should be between 3-30")
    private String productName;

    @NotNull
    @DecimalMin(value = "0.00")
    private Double price;

    private String description;

    @NotNull
    private String manufacturer;

    @NotNull
    @Min(value = 0)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

//	@ManyToMany(cascade = CascadeType.ALL)
//	private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Seller seller;

//	@ManyToMany
//	@JsonIgnore
//	private List<Cart> productCarts = new ArrayList<>();

}

