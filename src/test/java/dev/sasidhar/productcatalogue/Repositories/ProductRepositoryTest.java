package dev.sasidhar.productcatalogue.Repositories;

import dev.sasidhar.productcatalogue.Models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository prodrepo;

    @Test
    @Transactional
    public void testJPSMethds(){
        List<Product> A1 = prodrepo.findAllByOrderByPriceDesc();
        for(Product p: A1){
            System.out.println("name =" + p.getName() + " price = " + p.getPrice());
        }

    }
}
