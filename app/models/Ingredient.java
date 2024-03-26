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

/**
 * The Class Ingredient.
 */
@Entity
@Table(name = "INGREDIENTS")
public class Ingredient extends BaseModel {

    /** The name. */
    @Required(message = "Ingredient name parameter is required")
    @MaxLength(30)
    private String name;

    /** The family. */
    private String family;

    /** The recipes. */
    @JsonIgnore
    @ManyToMany(mappedBy = "ingredients")
    private List<Recipe> recipes;

    /** The Constant find. */
    public static final Finder<Long, Ingredient> find = new Finder<>(Ingredient.class);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the ingredient
     */
    public static Ingredient findById(Long id) {
	return find.byId(id);
    }

    /**
     * Find all.
     *
     * @return the list
     */
    public static List<Ingredient> findAll() {
	return find.all();

    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the list
     */
    public static List<Ingredient> findByName(String name) {
	return find.query().where().eq("name", name).findList();
    }

    /**
     * From json.
     *
     * @param json the json
     * @return the ingredient
     */
    public static Ingredient fromJson(JsonNode json) {
	return Json.fromJson(json, Ingredient.class);

    }

    /**
     * List from json.
     *
     * @param json the json
     * @return the list
     */
    public static List<Ingredient> listFromJson(JsonNode json) {

	List<Ingredient> ingredients = new ArrayList<>();

	json.forEach((node) -> ingredients.add(Ingredient.fromJson(node)));
	return ingredients;
    }

    /**
     * As json.
     *
     * @return the json node
     */
    public JsonNode asJson() {

	return Json.toJson(this);
    }

    /**
     * Instantiates a new ingredient.
     */
    public Ingredient() {
	super();
    }

    /**
     * Instantiates a new ingredient.
     *
     * @param name the name
     * @param family the family
     * @param recipes the recipes
     */
    public Ingredient(@Required(message = "Ingredient name parameter is required") @MaxLength(30) String name,
	    String family, List<Recipe> recipes) {
	super();
	this.name = name;
	this.family = family;
	this.recipes = recipes;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Gets the family.
     *
     * @return the family
     */
    public String getFamily() {
	return family;
    }

    /**
     * Sets the family.
     *
     * @param family the new family
     */
    public void setFamily(String family) {
	this.family = family;
    }

    /**
     * Gets the recipes.
     *
     * @return the recipes
     */
    public List<Recipe> getRecipes() {
	return recipes;
    }

    /**
     * Sets the recipes.
     *
     * @param recipes the new recipes
     */
    public void setRecipes(List<Recipe> recipes) {
	this.recipes = recipes;
    }

    /**
     * Gets the find.
     *
     * @return the find
     */
    public static Finder<Long, Ingredient> getFind() {
	return find;
    }

}
