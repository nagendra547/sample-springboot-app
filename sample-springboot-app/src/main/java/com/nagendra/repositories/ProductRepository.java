package com.nagendra.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nagendra.domain.Product;

/**
 * 
 * @author nagendra
 *
 */
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
