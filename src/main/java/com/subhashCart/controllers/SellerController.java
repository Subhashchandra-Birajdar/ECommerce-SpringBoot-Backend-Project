package com.subhashCart.controllers;

import com.subhashCart.dtos.SellerDTO;
import com.subhashCart.dtos.SessionDTO;
import com.subhashCart.models.Seller;
import com.subhashCart.services.SellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SellerController {

    @Autowired
    private SellerService sService;


    //Add seller-------------------------------------
    @PostMapping("/addseller")
    public ResponseEntity<Seller> addSellerHandler(@Valid @RequestBody Seller seller){

        Seller addseller=sService.addSeller(seller);

        System.out.println("Seller"+ seller);

        return new ResponseEntity<Seller>(addseller,HttpStatus.CREATED);
    }



    //Get the list of seller-----------------------
    @GetMapping("/sellers")
    public ResponseEntity<List<Seller>> getAllSellerHandler(){

        List<Seller> sellers=sService.getAllSellers();

        return new ResponseEntity<List<Seller>>(sellers, HttpStatus.OK);
    }


    //Get the seller by Id............................
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<Seller> getSellerByIdHandler(@PathVariable("sellerId") Integer Id){

        Seller getSeller=sService.getSellerById(Id);

        return new ResponseEntity<Seller>(getSeller, HttpStatus.OK);
    }


    // Get Seller by mobile Number
    @GetMapping("/seller")
    public ResponseEntity<Seller> getSellerByMobileHandler(
            @RequestParam("mobile") String mobile, @RequestHeader("token") String token){

        Seller getSeller=sService.getSellerByMobile(mobile, token);

        return new ResponseEntity<Seller>(getSeller, HttpStatus.OK);
    }


    // Get currently logged in seller
    @GetMapping("/seller/current")
    public ResponseEntity<Seller> getLoggedInSellerHandler(@RequestHeader("token") String token){
        Seller getSeller = sService.getCurrentlyLoggedInSeller(token);
        return new ResponseEntity<Seller>(getSeller, HttpStatus.OK);
    }

    //Update the seller..............................
    @PutMapping("/seller")
    public ResponseEntity<Seller> updateSellerHandler(
            @RequestBody Seller seller,
            @RequestHeader("token") String token){
        Seller updatedseller=sService.updateSeller(seller, token);
        return new ResponseEntity<Seller>(updatedseller,HttpStatus.ACCEPTED);
    }

    //Update the seller mobile..............................
    @PutMapping("/seller/update/mobile")
    public ResponseEntity<Seller> updateSellerMobileHandler(
            @Valid @RequestBody SellerDTO sellerdto,
            @RequestHeader("token") String token){
        Seller updatedseller=sService.updateSellerMobile(sellerdto, token);
        return new ResponseEntity<Seller>(updatedseller,HttpStatus.ACCEPTED);
    }

    //Update the seller password..............................
    @PutMapping("/seller/update/password")
    public ResponseEntity<SessionDTO> updateSellerPasswordHandler(
            @Valid @RequestBody SellerDTO sellerDto,
            @RequestHeader("token") String token){
        return new ResponseEntity<>(sService.updateSellerPassword(sellerDto, token), HttpStatus.ACCEPTED);
    }

    //delete the seller with seller id..............................
    @DeleteMapping("/seller/{sellerId}")
    public ResponseEntity<Seller> deleteSellerByIdHandler(
            @PathVariable("sellerId") Integer Id,
            @RequestHeader("token") String token){
        Seller deletedSeller=sService.deleteSellerById(Id, token);
        return new ResponseEntity<Seller>(deletedSeller,HttpStatus.OK);

    }
}