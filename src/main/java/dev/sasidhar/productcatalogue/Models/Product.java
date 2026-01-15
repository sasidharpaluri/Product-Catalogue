package dev.sasidhar.productcatalogue.Models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel {
    private String name;
    private String description;
    private double price;
    private String imageURL;
    private Category category;
}
