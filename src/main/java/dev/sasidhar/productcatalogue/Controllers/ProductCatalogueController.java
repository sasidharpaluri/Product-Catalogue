package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.DTOs.CategoryDTO;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Service.IProductservice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    public ProductCatalogueController(IProductservice productService) {
        this.productService = productService;
    }
    @PostMapping("/products")
    ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO prductResponseDTO = new ProductDTO();
          /*
        call the service layer to save the product
         */
        return prductResponseDTO;
    }
    @GetMapping("/products/{id}")
    ResponseEntity<ProductDTO> getProductbyID(@PathVariable("id") int id) {
        if(id < 1){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        Product product = productService.getProductById(id);

        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.convert(), HttpStatus.OK);
    }

    @GetMapping("/products")
    List<CategoryDTO> getAllProducts() {
        List<CategoryDTO> products = new ArrayList<>();
          /*
        call the service layer to get all products
         */
        return products;
    }

    // - for basic API Testing -//
    @GetMapping("/hello")
    String sayHello() {
        return "Hello you are seeing from local host of Sasidhar";
    }
}
