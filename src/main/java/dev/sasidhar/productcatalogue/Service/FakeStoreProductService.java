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

import java.util.List;

@Component
public class FakeStoreProductService implements IProductservice {
    private RestTemplate restTemplate;


//    public <T> ResponseEntity<T> postForEntity(String url, @Nullable Object request,
//                                               Class<T> responseType, @Nullable Object... uriVariables) throws RestClientException {
//
//        RequestCallback requestCallback = httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = responseEntityExtractor(responseType);
//        return nonNull(execute(url, HttpMethod.POST, requestCallback, responseExtractor, uriVariables));
//    }

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

    @Override
    public Product updateProduct(int id, Product product) {
        FakeStoreProductDTO request = product.convertToFake();
        HttpEntity<FakeStoreProductDTO> requestEntity = new HttpEntity<>(request);
        ResponseEntity<FakeStoreProductDTO> response = restTemplate.exchange("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,requestEntity,FakeStoreProductDTO.class,id);
        if(response.hasBody() && response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
                FakeStoreProductDTO resp = response.getBody();
                return resp.convert();
        }
        else {
            return null;
        }
    }
}
