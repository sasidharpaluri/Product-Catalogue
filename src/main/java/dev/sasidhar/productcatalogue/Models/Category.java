package dev.sasidhar.productcatalogue.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.*;

@Setter
@Getter
public class Category extends BaseModel{
    private String name;
    private List<Product> products;
}
