package dev.sasidhar.productcatalogue.Models;

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
    private String name;
}
