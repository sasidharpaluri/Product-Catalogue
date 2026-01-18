package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.DTOs.FakeStoreProductDTO;
import dev.sasidhar.productcatalogue.Models.Product;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreProductService implements IProductservice {
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDTO[]> response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            FakeStoreProductDTO[] fakeStoreProductDTOs = response.getBody();
            if (fakeStoreProductDTOs != null) {
                return List.of(fakeStoreProductDTOs).stream().map(FakeStoreProductDTO::convert).toList();
            }
        }
        return List.of();
    }

    @Override
    public Product getProductById(int id) {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity  = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,id);

        if(fakeStoreProductDTOResponseEntity.hasBody() && fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)))
        return fakeStoreProductDTOResponseEntity.getBody().convert();
        else
           return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
