package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.DTOs.ValidateTokenDto;
import dev.sasidhar.productcatalogue.Exceptions.LoginExpired;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Service.IProductservice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

 /*
    Create product ("/products"), POST
    Get product by id ("/products/{id}"), GET
    Get all products ("/products"), GET
    @RequestMapping("/products")
    @GetMapping
    @PostMapping
     */

@RestController
public class ProductCatalogueController {
    private IProductservice productService;
    private RestTemplate restTemplate;

    public ProductCatalogueController(IProductservice productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    private boolean validateToken(String token) {
        try {
            String url = "http://localhost:8080/User/validateToken";
            ValidateTokenDto dto = new ValidateTokenDto(token);
            ResponseEntity<String> response = restTemplate.postForEntity(url, dto, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateAdminToken(String token) {
        try {
            String url = "http://localhost:8080/User/validateAdminToken";
            ValidateTokenDto dto = new ValidateTokenDto(token);
            ResponseEntity<String> response = restTemplate.postForEntity(url, dto, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/products")
    ResponseEntity<ProductDTO> createProduct(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody ProductDTO productDTO) {
        if(token == null || !validateAdminToken(token)) {
            throw new LoginExpired("Please login again to continue");
        }
        Product product = productService.createProduct(productDTO);
        if(product == null)
                throw new IllegalArgumentException("Product already exists");
        return new ResponseEntity<>(product.convert(),HttpStatus.CREATED);
    }
    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductbyID(@PathVariable("id") int id) {
        if(id < 1){
            throw new IllegalArgumentException("Product ID must be greater than zero");
        }
        Product product = productService.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.convert(), HttpStatus.OK);
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if(products.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // Convert List<Product> to List<ProductDTO>
        return new ResponseEntity<>(products.stream().map(Product::convert).toList(),HttpStatus.OK);
       // return productService.getAllProducts().stream().map(Product::convert).toList();
    }

    @PatchMapping("/products/{id}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") int id,
                                             @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.convert(),HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    ResponseEntity<Boolean> deleteProduct(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable("id") int id){
        if(token == null || !validateAdminToken(token)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Boolean result = productService.deleteProduct(id);
        if(result)
            return new ResponseEntity<>(true,HttpStatus.OK);
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }
    // - for basic API Testing -//
    @GetMapping("/hello")
    String sayHello() {
        return "Hello you are seeing from local host of Sasidhar";
    }
}
