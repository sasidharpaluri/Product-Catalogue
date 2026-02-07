package dev.sasidhar.productcatalogue.Models;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    //@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    private Category category;
    private String image;
    private State state;
    private String type;

    public ProductDTO convert() {
        ProductDTO dto = new ProductDTO();
        dto.setId(this.getId());
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setType(this.type);
        dto.setImage(this.image);
        if (this.category != null) {
            dto.setCategory(this.category.getId());
            dto.setCategoryType(this.category.getName());
        }
        return dto;
    }
}
