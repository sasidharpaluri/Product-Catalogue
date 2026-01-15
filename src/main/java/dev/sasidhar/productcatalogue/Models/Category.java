package dev.sasidhar.productcatalogue.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class Category extends BaseModel{
    private String name;
    private String description;
    private List<Product> products;
}
