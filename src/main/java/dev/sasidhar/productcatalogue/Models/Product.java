package dev.sasidhar.productcatalogue.Models;

import dev.sasidhar.productcatalogue.DTOs.FakeStoreProductDTO;
import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
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

    public ProductDTO convert() {
        ProductDTO dto = new ProductDTO();
        dto.setId(this.getId());
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setState(this.state);
        dto.setImage(this.image);
        if (this.category != null) {
            dto.setCategory(this.category.convert());
        }
        return dto;
    }
    public FakeStoreProductDTO convertToFake() {
        FakeStoreProductDTO dto = new FakeStoreProductDTO();
        dto.setId(this.getId());
        dto.setTitle(this.name);
        dto.setDescription(this.description);
        dto.setPrice(this.price);
        dto.setImage(this.image);

        if (this.category != null) {
            dto.setCategory(this.category.getTitle());
        }
        return dto;
    }
}
