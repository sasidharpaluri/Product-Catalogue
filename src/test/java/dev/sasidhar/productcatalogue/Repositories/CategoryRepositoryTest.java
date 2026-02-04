package dev.sasidhar.productcatalogue.Repositories;

import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repo;

    @Test
    @Transactional
    public void testJPSMethds(){
        List<Category> A1 = repo.findAll();
        for(Category c: A1){
            System.out.println("id =" + c.getId() + " name = " + c.getName());
        }

    }

}