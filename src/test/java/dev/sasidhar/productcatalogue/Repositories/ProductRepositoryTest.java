package dev.sasidhar.productcatalogue.Repositories;
import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    ProductRepository prodRepo;

    @Test
    @Transactional
    public void Testing() {
        List<Product> A1 = prodRepo.findAllByOrderByPriceDesc();
        System.out.println("Test 1 --------------------");
        for (Product p : A1)
            System.out.println("name = " + p.getName() + " price = " + p.getPrice());


        List<Product> A2 = prodRepo.findByCategory_NameAndPriceGreaterThanOrderByPriceAsc("Wearables", 4000.00);
        System.out.println("Test 2 --------------------");
        for (Product p : A2)
            System.out.println("name = " + p.getName() + " price = " + p.getPrice());

        List<Product> A3 = prodRepo.findByNameContaining("c");
        System.out.println("Test 3 --------------------");
        for (Product p : A3)
            System.out.println("name = " + p.getName() + " price = " + p.getPrice());

        Optional<Product> A4 = prodRepo.findById(2);
        System.out.println("Test 4 --------------------");
        Product P = A4.get();
        Category C = P.getCategory();
        System.out.println(C.getName() + " " + C.getState() + " " + C.getId());
        System.out.println("name = " + A4.get().getName() + " price = " + A4.get().getPrice());

        List<Product> A5 = prodRepo.getAllProducts();
        System.out.println("Test 5 --------------------");
        for (Product p : A5)
            System.out.println("name = " + p.getName() + " price = " + p.getPrice());

        List<Product> A6 = prodRepo.getGreaterThan(5000.00);
        System.out.println("Test 6 --------------------");
        for (Product p : A6)
            System.out.println("name = " + p.getName() + " price = " + p.getPrice());

        List<Product> A7 = prodRepo.getByCategoryName("Wearables");
        System.out.println("Test 7 --------------------");
        for (Product p : A7)
            System.out.println("name = " + p.getName() + " price = " + p.getPrice());


    }
}