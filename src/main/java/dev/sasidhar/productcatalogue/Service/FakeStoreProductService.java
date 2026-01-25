package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.DTOs.FakeStoreProductDTO;
import dev.sasidhar.productcatalogue.Models.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class FakeStoreProductService implements IProductservice {
    private RestTemplate restTemplate;
    private WebClient webClient;

    public FakeStoreProductService(RestTemplate restTemplate, WebClient webClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @Override
    public List<Product> getAllProducts() {
       ResponseEntity<FakeStoreProductDTO[]> reponse = webClient.get()
                .uri("https://fakestoreapi.com/products/")
                .retrieve()
                .toEntity(FakeStoreProductDTO[].class)
                .block();
         if(reponse != null && reponse.hasBody() && reponse.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
                FakeStoreProductDTO[] dtoArray = reponse.getBody();
                if (dtoArray != null) {
                    return List.of(dtoArray).stream().map(FakeStoreProductDTO::convert).toList();
                }
         }
            return List.of();
    }

//    @Override
//    public Product getProductById(int id) {
//        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity  = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,id);
//
//        if(fakeStoreProductDTOResponseEntity.hasBody() && fakeStoreProductDTOResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200)))
//        return fakeStoreProductDTOResponseEntity.getBody().convert();
//        else
//           return null;
//    }

    @Override
    public Product getProductById(int id) {
        ResponseEntity<FakeStoreProductDTO> response  = webClient.get()
                .uri("https://fakestoreapi.com/products/{id}",id)
                .retrieve()
                .toEntity(FakeStoreProductDTO.class)
                .block();


       if(response != null && response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
           FakeStoreProductDTO dto = response.getBody();
           if (dto != null) {
               return dto.convert();
           }
       }
       return null;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDTO requestBody = product.convertToFake();
        ResponseEntity<FakeStoreProductDTO> response = webClient.post()
                .uri("https://fakestoreapi.com/products")
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(FakeStoreProductDTO.class)
                .block();

        if(response != null && response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(201))){
            FakeStoreProductDTO dto = response.getBody();
            if(dto != null)
                return dto.convert();
        }
        return null;
    }

//    @Override
//    public Product updateProduct(int id, Product product) {
//        FakeStoreProductDTO request = product.convertToFake();
//        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(request);
//        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,requestEntity,FakeStoreProductDTO.class,id);
//        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
//                FakeStoreProductDTO resp = response.getBody();
//                return resp.convert();
//        }
//        else {
//            return null;
//        }
//    }

    @Override
    public Product updateProduct(int id, Product product) {
        FakeStoreProductDTO requestBody = product.convertToFake();
        ResponseEntity<FakeStoreProductDTO> response  = webClient.put()
                .uri("https://fakestoreapi.com/products/{id}",id)
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(FakeStoreProductDTO.class)
                .block();


        if(response != null && response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            FakeStoreProductDTO dto = response.getBody();
            if (dto != null) {
                return dto.convert();
            }
        }
        return null;
    }
}
