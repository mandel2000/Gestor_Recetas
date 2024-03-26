package models;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import io.ebean.Finder;
import io.ebean.annotation.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import play.libs.Json;

/**
 * The Class User.
 */
@Entity
@Table(name = "USERS")
public class User extends BaseModel {

    /** The username. */
    @NotNull
    private String username;

    /** The password. */
    @NotNull
    private String password;

    /** The Constant find. */
    public static final Finder<Long, User> find = new Finder<>(User.class);

    /**
     * Find by id.
     *
     * @param id the id
     * @return the user
     */
    public static User findById(Long id) {
	return find.byId(id);
    }

    /**
     * Find all.
     *
     * @return the list
     */
    public static List<User> findAll() {
	return find.all();

    }

    /**
     * Find by username.
     *
     * @param username the username
     * @return the list
     */
    public static List<User> findByUsername(String username) {
	return find.query().where().eq("username", username).findList();
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
     * From json.
     *
     * @param json the json
     * @return the user
     */
    public static User fromJson(JsonNode json) {
	return Json.fromJson(json, User.class);

    }

    /**
     * Instantiates a new user.
     *
     * @param username the username
     * @param password the password
     */
    public User(String username, String password) {
	super();
	this.username = username;
	this.password = password;
    }

    /**
     * Instantiates a new user.
     */
    public User() {
	super();
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
	return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
	this.username = username;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
	this.password = password;
    }

}
