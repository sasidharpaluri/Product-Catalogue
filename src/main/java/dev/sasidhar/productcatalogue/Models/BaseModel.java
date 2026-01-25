package dev.sasidhar.productcatalogue.Models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private int id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private State state;
}
