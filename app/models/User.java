package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import play.libs.Json;

@Entity
@Table(name = "USERS")
public class User extends BaseModel {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public static final Finder<Long, User> find = new Finder<>(User.class);

    public static User findById(Long id) {
	return find.byId(id);
    }

    public static List<User> findAll() {
	return find.all();

    }

    public static List<User> findByUsername(String username) {
	return find.query().where().eq("username", username).findList();
    }

    public JsonNode asJson() {

	return Json.toJson(this);
    }

    public static User fromJson(JsonNode json) {
	return Json.fromJson(json, User.class);

    }

    public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
    }

    public User() {
	super();
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
