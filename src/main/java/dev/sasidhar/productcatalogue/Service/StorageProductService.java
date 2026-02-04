package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.DTOs.ProductDTO;
import dev.sasidhar.productcatalogue.Models.Category;
import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Models.State;
import dev.sasidhar.productcatalogue.Repositories.CategoryRepository;
import dev.sasidhar.productcatalogue.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductservice {
    private ProductRepository prodrepo;
    private CategoryRepository catrepo;

    public StorageProductService(ProductRepository prodrepo,CategoryRepository catrepo) {
        this.prodrepo = prodrepo;
        this.catrepo = catrepo;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> response = prodrepo.findAll();
        return response;
    }

    @Override
    public Product getProductById(int id) {
        Optional<Product> optionalProduct = prodrepo.findById(id);
        if(optionalProduct.isPresent())
        return optionalProduct.get();
        else
            return null;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) {
        Category category = catrepo.findById(productDTO.getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Product p = new Product();
        p.setId(productDTO.getId());
        p.setName(productDTO.getName());
        p.setDescription(productDTO.getDescription());
        p.setPrice(productDTO.getPrice());
        p.setImage(productDTO.getImage());
        p.setState(State.ACTIVE);
        p.setType(productDTO.getType());
        p.setCategory(category);
        if(prodrepo.findById(p.getId()).isEmpty()) {
            return prodrepo.save(p);
        }
        else
            return null;

    }

    @Override
    public Product updateProduct(int id, ProductDTO productDTO) {

        Product existingProd = getProductById(id);

        if( existingProd != null){
            /// ////////////////////////////////
            Optional<Category> category = catrepo.findById(productDTO.getId());

            Product p = new Product();
            p.setId(existingProd.getId());
            p.setCreatedAt(existingProd.getCreatedAt());
            p.setName(productDTO.getName());
            p.setDescription(productDTO.getDescription());
            p.setPrice(productDTO.getPrice());
            p.setImage(productDTO.getImage());
            p.setType(productDTO.getType());
            p.setCategory(category.get());
        /// ////////////////////////////////////////////////
            return prodrepo.save(p);

        }
        else
            return null;
    }
    public Boolean deleteProduct(int id){
        Product existingProd = getProductById(id);
        if(existingProd != null){
            existingProd.setState(State.DELETED);
            prodrepo.save(existingProd);
            return true;
        }
        else
            return true;
    }
}
