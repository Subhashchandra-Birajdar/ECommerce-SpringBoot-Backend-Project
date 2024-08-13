package com.subhashCart.services.impl;

import com.subhashCart.models.Product;
import com.subhashCart.repositories.ProductDao;
import com.subhashCart.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao prodDao;




    @Override
    public Product addProductToCatalog(Product product) {

        Product addedProduct = prodDao.save(product);



        return addedProduct;
    }

    @Override
    public Product getProductFromCatalogById(Integer id) {

        Optional<Product> opt =   prodDao.findById(id);

        return opt.get();
    }

    @Override
    public String deleteProductFromCatalog(Integer id) {
        Optional<Product> opt  = prodDao.findById(id);

        Product prod = opt.get();
        prodDao.delete(prod);

        return "Product deleted from catalog";
    }

    @Override
    public Product updateProductInCatalog(Product prod) {

        Optional<Product> opt =  prodDao.findById(prod.getProductId());

        if(opt.isPresent()) {
            Product existingProduct = 	opt.get();
            Product prod1 = prodDao.save(prod);
            return prod1;
        }

        return null;
    }



}
