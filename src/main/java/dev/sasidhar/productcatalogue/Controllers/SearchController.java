package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.DTOs.SearchDTO;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ISearchService searchService;

    @PostMapping()
    public ResponseEntity<List<ProductDTO>> search(@RequestBody SearchDTO searchdto){
        List<Product> products = searchService.searchProducts(searchdto.getQuery(),searchdto.getPageNo(),searchdto.getPageSize(),searchdto.getSortTypeInfos());
        return new ResponseEntity<>(products.stream().map(Product::convert).toList(), HttpStatus.OK);
    }
}
