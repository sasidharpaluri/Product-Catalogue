package dev.sasidhar.productcatalogue.DTOs;

import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
public class CategoryDTO {
    private int categoryId;
    private String categoryName;
    private List<ProductDTO> products;

    public Category convert() {
        Category category = new Category();
        category.setId(this.categoryId);
        category.setName(this.categoryName);
        if(this.products != null) {
            List<Product> productList = this.products.stream()
                    .map(ProductDTO::convert)
                    .toList();
            category.setProducts(productList);
        } else {
            category.setProducts(null);
        }
        return category;
    }
}
