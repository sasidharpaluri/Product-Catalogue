package dev.sasidhar.productcatalogue.Repositories;

import dev.sasidhar.productcatalogue.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
