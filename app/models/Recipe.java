package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import play.libs.Json;

/**
 * The Class Recipe.
 */
@Entity
@Table(name = "RECIPES")
public class Recipe extends BaseModel {

    /** The title. */
    private String title;

    /** The description. */
    private String description;

    /** The duration. */
    private Integer duration;

    /** The author. */
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = true)
    private Author author;

    /** The ingredients. */
    @ManyToMany
    @JoinTable(name = "recipe_ingredient", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;

    /** The Constant find. */
    public static final Finder<Long, Recipe> find = new Finder<>(Recipe.class);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the recipe
     */
    public static Recipe findById(Long id) {
	return find.byId(id);
    }

    /**
     * Find all.
     *
     * @return the list
     */
    public static List<Recipe> findAll() {
	return find.all();

    }

    /**
     * Find by title.
     *
     * @param title the title
     * @return the list
     */
    public static List<Recipe> findByTitle(String title) {
	return find.query().where().eq("title", title).findList();
    }

    /**
     * From json.
     *
     * @param json the json
     * @return the recipe
     */
    public static Recipe fromJson(JsonNode json) {
	return Json.fromJson(json, Recipe.class);

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
     * Instantiates a new recipe.
     *
     * @param title       the title
     * @param description the description
     * @param duration    the duration
     * @param author      the author
     * @param ingredients the ingredients
     */
    public Recipe(String title, String description, Integer duration, Author author, List<Ingredient> ingredients) {
	super();
	this.title = title;
	this.description = description;
	this.duration = duration;
	this.author = author;
	this.ingredients = ingredients;
    }

    /**
     * Instantiates a new recipe.
     */
    public Recipe() {
	super();
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
	return description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public Integer getDuration() {
	return duration;
    }

    /**
     * Sets the duration.
     *
     * @param duration the new duration
     */
    public void setDuration(Integer duration) {
	this.duration = duration;
    }

    /**
     * Gets the author.
     *
     * @return the author
     */
    public Author getAuthor() {
	return author;
    }

    /**
     * Sets the author.
     *
     * @param author the new author
     */
    public void setAuthor(Author author) {
	this.author = author;
    }

    /**
     * Gets the ingredients.
     *
     * @return the ingredients
     */
    public List<Ingredient> getIngredients() {
	return ingredients;
    }

    /**
     * Sets the ingredients.
     *
     * @param ingredients the new ingredients
     */
    public void setIngredients(List<Ingredient> ingredients) {
	this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
	this.ingredients.add(ingredient);
    }

}
