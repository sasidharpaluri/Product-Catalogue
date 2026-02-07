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
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    @Transactional
    public void testJPSMethds(){
    // Get all Categories with associated products

        List<Category> categories = repo.findAll();
        for(Category c : categories) {
            System.out.println(c.getName());
            for(Product p : c.getProducts())
                System.out.println(p.getName());
        }
//        Optional<Category> A2 = repo.findById(1);
//
//        if(A2.isPresent()){
//            Category cat = A2.get();
//            System.out.println("All prod belonging to : " + cat.getName() + " are ");
//            for(Product prod : cat.getProducts())
//                    System.out.println(prod.getName());
//        }

    }

}