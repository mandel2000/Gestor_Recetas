package models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import play.libs.Json;

@Entity
@Table(name = "RECIPES")
public class Recipe extends BaseModel {

    private String title;

    private String description;

    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> ingredients;

    public static final Finder<Long, Recipe> find = new Finder<>(Recipe.class);

    public static Recipe findById(Long id) {
	return find.byId(id);
    }

    public static List<Recipe> findAll() {
	return find.all();

    }

    public static List<Recipe> findByTitle(String title) {
	return find.query().where().eq("title", title).findList();
    }

    public static Recipe fromJson(JsonNode json) {
	return Json.fromJson(json, Recipe.class);

    }

    public JsonNode asJson() {
	return Json.toJson(this);
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Integer getDuration() {
	return duration;
    }

    public void setDuration(Integer duration) {
	this.duration = duration;
    }

    public Author getAuthor() {
	return author;
    }

    public void setAuthor(Author author) {
	this.author = author;
    }

    public Set<RecipeIngredient> getIngredients() {
	return ingredients;
    }

    public void setIngredients(Set<RecipeIngredient> ingredients) {
	this.ingredients = ingredients;
    }

}
