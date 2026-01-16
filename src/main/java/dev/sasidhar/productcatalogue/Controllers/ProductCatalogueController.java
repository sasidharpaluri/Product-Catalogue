package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.DTOs.ProductRequestDTO;
import dev.sasidhar.productcatalogue.DTOs.ProductResponseDTO;
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

    @PostMapping("/products")
    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
          /*
        call the service layer to save the product
         */
        return productResponseDTO;
    }
    @GetMapping("/products/{id}")
    ProductResponseDTO getProductbyID(@PathVariable("id") int id) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
          /*
        call the service layer to get the product with id
         */
        return productResponseDTO;
    }

    List<ProductResponseDTO> getAllProducts() {
        List<ProductResponseDTO> products = new ArrayList<>();
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
