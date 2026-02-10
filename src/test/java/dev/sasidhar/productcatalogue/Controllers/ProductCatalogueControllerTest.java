package dev.sasidhar.productcatalogue.Controllers;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Service.IProductservice;
import dev.sasidhar.productcatalogue.Service.StorageProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductCatalogueControllerTest {

    @Autowired
    private ProductCatalogueController productController;

    @MockBean
    private StorageProductService productService;

    @Test
    public void testGetProduct_ById(){
        Product prod = new Product();
        prod.setId(1);
        prod.setName("Dummy");
        prod.setImage("www.image.com");
        prod.setDescription("Dummy description");
        prod.setPrice(15151.023);

        when(productService.getProductById(1)).thenReturn(prod);

        ResponseEntity<ProductDTO> response = productController.getProductbyID(1);

        /*
        Assert Step
         */
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(prod.getId(),response.getBody().getId());
        assertEquals(prod.getName(),response.getBody().getName());

        verify(productService, times(1)).getProductById(1);
    }

    @Test
    public void testGetProduct_ById_negative(){
        assertThrows(IllegalArgumentException.class,()->productController.getProductbyID(-1));
    }
}