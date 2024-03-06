package models;

import jakarta.persistence.Entity;

@Entity
public class Recipe extends BaseModel {

    private String title;

    private String description;

}
