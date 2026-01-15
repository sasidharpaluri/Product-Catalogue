package dev.sasidhar.productcatalogue.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCatalogueController {

    @GetMapping("/products/{id}")
    String getAllProducts(@PathVariable("id") int id,
                       @RequestParam("Quantity") int quantity) {
        return id*quantity>100?"notAvailable":"available total price = " + (id*quantity);
    }

    @GetMapping("/hello")
    String sayHello() {
        return "Hello you are seeing from local host of Sasidhar";
    }
}
