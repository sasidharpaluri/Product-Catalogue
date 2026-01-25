package dev.sasidhar.productcatalogue.DTOs;

import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Models.State;
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
    private State categoryState;
    private List<ProductDTO> products;

    public Category convert() {
        Category category = new Category();
        category.setId(this.categoryId);
        category.setState(this.categoryState);
        category.setTitle(this.categoryName);
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
