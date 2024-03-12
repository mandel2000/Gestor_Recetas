package models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RECIPES")
public class Recipe extends BaseModel {

    private String title;

    private String description;

    @JoinTable(name = "RECIPES_INGREDIENTS", joinColumns = @JoinColumn(name = "ID_RECIPE", nullable = false), inverseJoinColumns = @JoinColumn(name = "ID_INGREDIENT", nullable = false))
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredient;

}
