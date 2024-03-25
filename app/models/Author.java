package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import play.libs.Json;

@Entity
@Table(name = "AUTHORS")
public class Author extends BaseModel {

    private String name;

    private String surname;

    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Recipe> recipes;

    public static final Finder<Long, Author> find = new Finder<>(Author.class);

    public static Author findById(Long id) {
	return find.byId(id);
    }

    public static List<Author> findAll() {
	return find.all();

    }

    public static List<Author> findByName(String name) {
	return find.query().where().eq("name", name).findList();
    }

    public static List<Author> findByNameAndSurname(String name, String surname) {
	return find.query().where().eq("name", name).and().eq("surname", surname).findList();
    }

    public static Author fromJson(JsonNode json) {

	return Json.fromJson(json, Author.class);

    }

    public JsonNode asJson() {
	return Json.toJson(this);
    }

    public Author() {
	super();
    }

    public Author(String name, String surname, String nationality, List<Recipe> recipes) {
	super();
	this.name = name;
	this.surname = surname;
	this.nationality = nationality;
	this.recipes = recipes;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getSurname() {
	return surname;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public String getNationality() {
	return nationality;
    }

    public void setNationality(String nationality) {
	this.nationality = nationality;
    }

    public static Finder<Long, Author> getFind() {
	return find;
    }

}
