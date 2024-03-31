package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.ValidateWith;
import play.libs.Json;
import validators.DniValidator;

@Entity
public class UserInfo extends BaseModel {

    @Required
    private String name;
    @Required
    private String surname;

    @Required
    @ValidateWith(DniValidator.class)
    private String dni;

    private Integer edad;

    @OneToOne
    private User user;

    public static Finder<Long, UserInfo> find = new Finder<>(UserInfo.class);

    public static UserInfo findById(Long id) {
	return find.byId(id);
    }

    public static List<UserInfo> findAll() {
	return find.all();

    }

    public static List<UserInfo> findByName(String name) {
	return find.query().where().eq("name", name).findList();
    }

    public static List<UserInfo> findByDni(String dni) {
	return find.query().where().eq("dni", dni).findList();
    }

    public JsonNode asJson() {

	return Json.toJson(this);
    }

    public static User fromJson(JsonNode json) {
	return Json.fromJson(json, User.class);

    }

    public UserInfo() {
	super();
    }

    public UserInfo(String name, String surname, String dni, Integer edad, User user) {
	super();
	this.name = name;
	this.surname = surname;
	this.dni = dni;
	this.edad = edad;
	this.user = user;
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

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public Integer getEdad() {
	return edad;
    }

    public void setEdad(Integer edad) {
	this.edad = edad;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public static Finder<Long, UserInfo> getFind() {
	return find;
    }

    public static void setFind(Finder<Long, UserInfo> find) {
	UserInfo.find = find;
    }

}
