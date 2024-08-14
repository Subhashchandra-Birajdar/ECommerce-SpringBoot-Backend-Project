package com.subhashCart.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    //private Integer productId;
    private String prodName;
    private String manufaturer;
    private Double price;
    private Integer quantity;

}
