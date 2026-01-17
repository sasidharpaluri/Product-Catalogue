package dev.sasidhar.productcatalogue.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private int id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
