package com.subhashCart.services;

import com.subhashCart.models.Product;

public interface ProductService {

    public Product addProductToCatalog(Product product);

    public Product getProductFromCatalogById(Integer id);

    public String deleteProductFromCatalog(Integer id);

    public Product updateProductInCatalog(Product product);

}
