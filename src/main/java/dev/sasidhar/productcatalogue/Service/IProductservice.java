package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.Models.Product;

import java.util.List;

public interface IProductservice {
    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public Product createProduct(ProductDTO productDTO);
    public Product updateProduct(int id,ProductDTO productDTO);
    public Boolean deleteProduct(int id);

}
