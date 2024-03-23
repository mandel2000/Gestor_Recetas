package models;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.OneToMany;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.libs.Json;

public class Ingredient extends BaseModel {

    @Required(message = "Ingredient name parameter is required")
    @MaxLength(30)
    private String name;

    private String family;

    @OneToMany(mappedBy = "ingredient")
    private Set<RecipeIngredient> recipes;

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

    public JsonNode asJson() {
	return Json.toJson(this);
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

    public static Finder<Long, Ingredient> getFind() {
	return find;
    }

}
