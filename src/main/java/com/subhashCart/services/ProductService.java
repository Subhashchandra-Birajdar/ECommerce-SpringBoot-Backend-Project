package com.subhashCart.services;

import com.subhashCart.dtos.ProductDTO;
import com.subhashCart.models.Product;
import com.subhashCart.models.enums.CategoryEnum;
import com.subhashCart.models.enums.ProductStatus;

import java.util.List;

public interface ProductService {

    public Product addProductToCatalog(String token, Product product);

    public Product getProductFromCatalogById(Integer id);

    public String deleteProductFromCatalog(Integer id);

    public Product updateProductIncatalog(Product product);

    public List<Product> getAllProductsIncatalog();

    public List<ProductDTO> getAllProductsOfSeller(Integer id);

    public List<ProductDTO> getProductsOfCategory(CategoryEnum catenum);

    public List<ProductDTO> getProductsOfStatus(ProductStatus status);



    public Product updateProductQuantityWithId(Integer id,ProductDTO prodDTO);


}
