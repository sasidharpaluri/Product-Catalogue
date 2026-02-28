package dev.sasidhar.productcatalogue.Service;

import dev.sasidhar.productcatalogue.Models.Product;
import dev.sasidhar.productcatalogue.Pojos.SortTypeInfo;
import dev.sasidhar.productcatalogue.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SearchService implements ISearchService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> searchProducts(String query, Integer pageNo, Integer pageSize, List<SortTypeInfo> sortTypeInfos) {

        Sort sort = null;

        if(sortTypeInfos != null && !sortTypeInfos.isEmpty()){
            if(sortTypeInfos.get(0).getSortType().equals("ASC")){
                sort = Sort.by(sortTypeInfos.get(0).getSortBy());
            }
            else
                sort = Sort.by(sortTypeInfos.get(0).getSortBy()).descending();

            for(int i=1;i<sortTypeInfos.size();i++){
                if(sortTypeInfos.get(i).getSortType().equals("ASC")){
                    sort = sort.and(Sort.by(sortTypeInfos.get(i).getSortBy()) );
                }
                else
                    sort = sort.and(Sort.by(sortTypeInfos.get(i).getSortBy()).descending());
            }
        }

    return productRepository.findByType(query, PageRequest.of(pageNo, pageSize, sort));

    }
}
