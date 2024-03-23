package models;

import java.util.List;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import play.data.validation.Constraints.Required;

@Entity
public class RecipeIngredient extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Ingredient ingredient;

    @Required
    private double quantity;

    @Required
    private String measuringUnit;

    public static final Finder<Long, RecipeIngredient> find = new Finder<>(RecipeIngredient.class);

    public static RecipeIngredient findById(Long id) {
	return find.byId(id);
    }

    public static List<RecipeIngredient> findAll() {
	return find.all();

    }

    public Recipe getRecipe() {
	return recipe;
    }

    public void setRecipe(Recipe recipe) {
	this.recipe = recipe;
    }

    public Ingredient getIngredient() {
	return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
	this.ingredient = ingredient;
    }

    public double getQuantity() {
	return quantity;
    }

    public void setQuantity(double quantity) {
	this.quantity = quantity;
    }

    public String getMeasuringUnit() {
	return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
	this.measuringUnit = measuringUnit;
    }

}
