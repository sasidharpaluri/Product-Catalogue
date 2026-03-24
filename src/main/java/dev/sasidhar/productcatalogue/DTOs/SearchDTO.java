package dev.sasidhar.productcatalogue.DTOs;

import dev.sasidhar.productcatalogue.Pojos.SortTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDTO{
    private String query;
    private Integer pageNo;
    private Integer pageSize;
    private List<SortTypeInfo> sortTypeInfos;
}
