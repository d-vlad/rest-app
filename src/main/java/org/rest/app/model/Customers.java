package org.rest.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name="customers")
public class Customers {

    @Id
    Integer id;
    String name;

}
