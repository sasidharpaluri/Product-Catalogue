package dev.sasidhar.productcatalogue.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Service.IProductservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@WebMvcTest(ProductCatalogueController.class)
public class productCatalogueMVCtest {
    @MockBean
    private IProductservice service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllProducts() throws Exception {
        Product prod = new Product();
        prod.setId(1);
        prod.setName("Dummy");
        prod.setImage("www.image.com");
        prod.setDescription("Dummy description");
        prod.setPrice(15151.023);
        prod.setType("type of product");
        Category c = new Category();
        c.setName("Cat ntg");;
        c.setId(12);
        prod.setCategory(c);

        List<Product> products = new ArrayList<>();
        products.add(prod);

        when(service.getAllProducts()).thenReturn(products);

        ProductDTO prodDTO = prod.convert();

        List<ProductDTO> expectedProducts = new ArrayList<>();
        expectedProducts.add(prodDTO);

        ObjectMapper objectMapper = new ObjectMapper();

        String responseJson = objectMapper.writeValueAsString(expectedProducts);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

//        mockMvc.perform(get("/products")).
//                andExpect(status().isOk())
//                .andExpect(content().json(response));



    }

    public void testPostProduct(){

    }

}
