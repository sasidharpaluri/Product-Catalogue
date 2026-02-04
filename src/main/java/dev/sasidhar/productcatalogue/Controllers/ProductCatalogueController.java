package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Service.IProductservice;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public ProductCatalogueController(@Qualifier("storageProductService") IProductservice productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
       // ProductDTO prductResponseDTO = new ProductDTO();
        Product product = productService.createProduct(productDTO);
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

    @PutMapping("/products/{id}")
    ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") int id,
                                             @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        if(product == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.convert(),HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id){
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
