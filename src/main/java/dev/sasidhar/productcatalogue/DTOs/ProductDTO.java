package dev.sasidhar.productcatalogue.DTOs;

import dev.sasidhar.productcatalogue.Models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProductDTO {
    private int productId;
    private String name;
    private String description;
    private double price;
    private String image;
    private Category category;
}
