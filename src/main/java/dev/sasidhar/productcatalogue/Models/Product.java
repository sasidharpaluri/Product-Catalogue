package dev.sasidhar.productcatalogue.Models;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    private Category category;
    private String image;

    public ProductDTO convert() {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(this.getId());
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setImage(this.image);
        if (this.category != null) {
            dto.setCategory(this.category);
        }
        return dto;
    }
}
