package dev.sasidhar.productcatalogue.Models;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

import java.util.*;

@Setter
@Getter
@Entity
public class Category extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @BatchSize(size = -60)
    private List<Product> products;
}
