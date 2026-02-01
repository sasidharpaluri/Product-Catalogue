package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Models.State;
import dev.sasidhar.productcatalogue.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements IProductservice {
    private ProductRepository prodrepo;

    public StorageProductService(ProductRepository prodrepo) {
        this.prodrepo = prodrepo;
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
    public Product createProduct(Product product) {
        if(prodrepo.findById(product.getId()).isEmpty()) {
            return prodrepo.save(product);
        }
        else
            return null;

    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product existingProd = getProductById(id);
        if( existingProd != null){
            product.setId(id);
            product.setCreatedAt(existingProd.getCreatedAt());
            return prodrepo.save(product);

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
