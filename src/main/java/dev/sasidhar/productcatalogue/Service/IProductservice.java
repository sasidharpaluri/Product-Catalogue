package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.Models.Product;

import java.util.List;

public interface IProductservice {
    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public Product createProduct(Product product);
    public Product updateProduct(int id,Product product);

}
