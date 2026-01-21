package dev.sasidhar.productcatalogue.Models;

import dev.sasidhar.productcatalogue.DTOs.CategoryDTO;
import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Setter
@Getter
public class Category extends BaseModel{
    private String name;
    private List<Product> products;

    public CategoryDTO convert(){
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(this.getId());
        dto.setCategoryName(this.name);
        if(this.products != null){
            List<ProductDTO> p = this.products.stream().map(Product::convert).toList();
            dto.setProducts(p);
        }
        dto.setProducts(null);
        return dto;
    }
}
