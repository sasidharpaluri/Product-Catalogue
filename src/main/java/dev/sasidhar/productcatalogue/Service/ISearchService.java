package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Pojos.SortTypeInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {
    List<Product> searchProducts(String query, Integer pageNo, Integer pageSize, List<SortTypeInfo> sortTypeInfos);

}
