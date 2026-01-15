package dev.sasidhar.productcatalogue.Models;

import java.util.Date;

public abstract class BaseModel {
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
