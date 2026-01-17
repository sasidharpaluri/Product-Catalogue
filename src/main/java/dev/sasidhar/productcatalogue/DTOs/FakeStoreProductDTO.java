package dev.sasidhar.productcatalogue.DTOs;


import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class FakeStoreProductDTO {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;

    public Product convert(){
        Product p = new Product();
        p.setId(this.id);
        p.setPrice(this.price);
        p.setDescription(this.description);
        p.setName(this.title);
        Category c = new Category();
            c.setName(this.category);
        p.setCategory(c);
        p.setImage(this.image);
        return p;
    }


}
