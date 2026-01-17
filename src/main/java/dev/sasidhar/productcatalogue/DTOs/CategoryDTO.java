package dev.sasidhar.productcatalogue.DTOs;

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
    private List<Product> products;

}
