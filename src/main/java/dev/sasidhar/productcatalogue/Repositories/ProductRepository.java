package dev.sasidhar.productcatalogue.Repositories;

import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByOrderByPriceDesc();
    List<Product> findByPriceGreaterThan(double price);
    List<Product> findByPriceGreaterThanOrderByPriceAsc(double price);
    List<Product> findByNameContaining(String name);
    List<Product> findByCategory_NameAndPriceGreaterThanOrderByPriceAsc(String category_name, double price);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    List<Product> getAllProducts();

    @Query("SELECT p FROM Product p WHERE p.price > :price " +
            "ORDER BY p.price DESC")
    List<Product> getGreaterThan(@Param("price") double price);

    @Query("""
SELECT p
FROM Product p
WHERE p.category.name = :name
ORDER BY p.price DESC
""")
    List<Product> getByCategoryName(@Param("name") String name);

}
