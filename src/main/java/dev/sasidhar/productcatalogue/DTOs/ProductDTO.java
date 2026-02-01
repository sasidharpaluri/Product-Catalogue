package dev.sasidhar.productcatalogue.DTOs;

import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Models.State;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private String image;
    private CategoryDTO category;
    private State state;

    public Product convert() {
        Product p = new Product();
        p.setId(this.id);
        p.setName(this.name);
        p.setDescription(this.description);
        p.setPrice(this.price);
        p.setImage(this.image);
        p.setState(this.state);
        Category category1 = category.convert();
        p.setCategory(category1);
        return p;
    }
}
