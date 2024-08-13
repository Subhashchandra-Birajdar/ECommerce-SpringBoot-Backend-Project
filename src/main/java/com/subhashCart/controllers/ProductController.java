package com.subhashCart.controllers;

import com.subhashCart.models.Product;
import com.subhashCart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService pService;

    //this method adds new product to catalog by seller and returns added product

    @PostMapping("/products")
    public ResponseEntity<Product> addProductToCatalogHandler(@RequestBody Product product){

        Product prod = pService.addProductToCatalog(product);

        return new ResponseEntity<Product>(prod, HttpStatus.ACCEPTED);

    }


    //This method gets the product which needs to be added to the cart returns product

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductFromCatalogByIdHandler(@PathVariable("id") Integer id){

        Product prod = pService.getProductFromCatalogById(id);

        return new ResponseEntity<Product>(prod,HttpStatus.FOUND);

    }


    //This method will delete the product from catalog and returns the response
    //This will be called only when the product qty will be zero or seller wants to delete for any other reason

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductFromCatalogHandler(@PathVariable("id") Integer id){

        String res = pService.deleteProductFromCatalog(id);
        return new ResponseEntity<String>(res,HttpStatus.OK);
    }

    public ResponseEntity<Product> updateProductInCatalogHandler(@RequestBody Product prod){

        Product prod1 = pService.updateProductInCatalog(prod);

        return new ResponseEntity<Product>(prod1,HttpStatus.OK);

    }
}
