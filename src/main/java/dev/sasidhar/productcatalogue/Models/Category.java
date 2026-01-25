package dev.sasidhar.productcatalogue.Models;

import dev.sasidhar.productcatalogue.DTOs.CategoryDTO;
import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Setter
@Getter
@Entity
public class Category extends BaseModel{
    private String title;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public CategoryDTO convert(){
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(this.getId());
        dto.setCategoryName(this.title);
        if(this.products != null){
            List<ProductDTO> p = this.products.stream().map(Product::convert).toList();
            dto.setProducts(p);
        }
        dto.setProducts(null);
        return dto;
    }
}
