package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import play.libs.Json;

/**
 * The Class Author.
 */
@Entity
@Table(name = "AUTHORS")
public class Author extends BaseModel {

    /** The name. */
    private String name;

    /** The surname. */
    private String surname;

    /** The nationality. */
    private String nationality;

    /** The recipes. */
    @OneToMany(mappedBy = "author")
    private List<Recipe> recipes;

    /** The Constant find. */
    public static final Finder<Long, Author> find = new Finder<>(Author.class);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the author
     */
    public static Author findById(Long id) {
	return find.byId(id);
    }

    /**
     * Find all.
     *
     * @return the list
     */
    public static List<Author> findAll() {
	return find.all();

    }

    /**
     * Find by name.
     *
     * @param name the name
     * @return the list
     */
    public static List<Author> findByName(String name) {
	return find.query().where().eq("name", name).findList();
    }

    /**
     * Find by name and surname.
     *
     * @param name the name
     * @param surname the surname
     * @return the list
     */
    public static List<Author> findByNameAndSurname(String name, String surname) {
	return find.query().where().eq("name", name).and().eq("surname", surname).findList();
    }

    /**
     * From json.
     *
     * @param json the json
     * @return the author
     */
    public static Author fromJson(JsonNode json) {

	return Json.fromJson(json, Author.class);

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
     * Instantiates a new author.
     */
    public Author() {
	super();
    }

    /**
     * Instantiates a new author.
     *
     * @param name the name
     * @param surname the surname
     * @param nationality the nationality
     * @param recipes the recipes
     */
    public Author(String name, String surname, String nationality, List<Recipe> recipes) {
	super();
	this.name = name;
	this.surname = surname;
	this.nationality = nationality;
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
     * Gets the surname.
     *
     * @return the surname
     */
    public String getSurname() {
	return surname;
    }

    /**
     * Sets the surname.
     *
     * @param surname the new surname
     */
    public void setSurname(String surname) {
	this.surname = surname;
    }

    /**
     * Gets the nationality.
     *
     * @return the nationality
     */
    public String getNationality() {
	return nationality;
    }

    /**
     * Sets the nationality.
     *
     * @param nationality the new nationality
     */
    public void setNationality(String nationality) {
	this.nationality = nationality;
    }

    /**
     * Gets the find.
     *
     * @return the find
     */
    public static Finder<Long, Author> getFind() {
	return find;
    }

}
