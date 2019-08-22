package com.nagendra.services;


import com.nagendra.domain.Product;

/**
 * 
 * @author nagendra
 *
 */
public interface ProductService {
    Iterable<Product> listAllProducts();

    Product getProductById(Integer id);

    Product saveProduct(Product product);

    void deleteProduct(Integer id);
}
