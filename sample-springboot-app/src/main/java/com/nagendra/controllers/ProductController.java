package com.nagendra.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.nagendra.domain.Product;
import com.nagendra.services.ProductService;

/**
 * 
 * @author nagendra
 *
 */
@RestController
@RequestMapping("/product")
@Api(value = "onlinestore", description = "Operations pertaining to products in Online Store")
public class ProductController {

	private ProductService productService;
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "View a list of available products", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public Iterable<Product> list(Model model) {
		logger.info("Listing All Products");
		Iterable<Product> productList = productService.listAllProducts();
		return productList;
	}

	@ApiOperation(value = "Search a product with an ID", response = Product.class)
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
	public Product showProduct(@PathVariable Integer id, Model model) {
		Product product = productService.getProductById(id);
		return product;
	}

	@ApiOperation(value = "Add a product")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity saveProduct(@RequestBody Product product) {
		logger.info("Creating Product with product name : " + product.getName());
		productService.saveProduct(product);
		return new ResponseEntity("Product saved successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Update a product")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		Product storedProduct = productService.getProductById(id);
		storedProduct.setPrice(product.getPrice());
		productService.saveProduct(storedProduct);
		return new ResponseEntity("Product updated successfully", HttpStatus.OK);
	}

	@ApiOperation(value = "Show Total count of products")
	@RequestMapping(value = "/count", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity showTotalCount() {
		
		Iterable<Product> listAllProducts = productService.listAllProducts();
		int count = 0;
		for (Product p : listAllProducts) {
			count++;
		}
		logger.info("Getting total count of  Products: "+ count); 
		return new ResponseEntity(Integer.toString(count), HttpStatus.OK);

	}

	 @ApiOperation(value = "Delete a product")
	 @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE,
	 produces = "application/json")
	 public ResponseEntity delete(@PathVariable Integer id){
		 logger.info("Deleting Product with ID:  "+ id); 
	 productService.deleteProduct(id);
	 return new ResponseEntity("Product deleted successfully", HttpStatus.OK);
	
	 }

}
