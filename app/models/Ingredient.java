package models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.libs.Json;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient extends BaseModel {

    @Required(message = "Ingredient name parameter is required")
    @MaxLength(30)
    private String name;

    private String family;

    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    public static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    public static Ingredient findById(Long id) {
	return find.byId(id);
    }

    public static List<Ingredient> findAll() {
	return find.all();

    }

    public static List<Ingredient> findByName(String name) {
	return find.query().where().eq("name", name).findList();
    }

    public static Ingredient fromJson(JsonNode json) {
	return Json.fromJson(json, Ingredient.class);

    }

    public static List<Ingredient> listFromJson(JsonNode json) {

	List<Ingredient> ingredients = new ArrayList<>();

	json.forEach((node) -> ingredients.add(Ingredient.fromJson(node)));
	return ingredients;
    }

    public JsonNode asJson() {

	return Json.toJson(this);
    }

    public Ingredient() {
	super();
    }

    public Ingredient(@Required(message = "Ingredient name parameter is required") @MaxLength(30) String name,
	    String family, List<Recipe> recipes) {
	super();
	this.name = name;
	this.family = family;
	this.recipes = recipes;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getFamily() {
	return family;
    }

    public void setFamily(String family) {
	this.family = family;
    }

    public List<Recipe> getRecipes() {
	return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
	this.recipes = recipes;
    }

    public static Finder<Long, Ingredient> getFind() {
	return find;
    }

}
