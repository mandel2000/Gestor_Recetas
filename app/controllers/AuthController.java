package controllers;

import java.util.List;

import auth.JwtAuthorizationUtils;
import models.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import utils.PasswordEncryptUtils;

public class AuthController extends Controller {

    public Result create(Http.Request request) {

	try {

	    User user = User.fromJson(request.body().asJson());

	    if (User.findByUsername(user.getUsername()).isEmpty()) {

		user.setPassword(PasswordEncryptUtils.encrypt(user.getPassword()));
		user.save();

		if (request.accepts("application/xml")) {

		    return Results.created(views.xml.userXml.render(user)).as("application/xml");

		} else if (request.accepts("application/json")) {

		    return Results.created(user.asJson()).as("application/json");

		} else {

		    return badRequest("Unsupported format");
		}
	    }

	    return found("Error, user already exists");

	} catch (Exception e) {
	    return internalServerError(e.getMessage());
	}
    }

    public Result login(Http.Request request) {

	try {

	    User user = User.fromJson(request.body().asJson());

	    List<User> registeredUser = User.findByUsername(user.getUsername());

	    if (!registeredUser.isEmpty()) {

		if (PasswordEncryptUtils.checkPassword(user.getPassword(), registeredUser.get(0).getPassword())) {
		    String jsonTemplate = "{\"token\":\"%s\"}";
		    String token = JwtAuthorizationUtils.generateToken(user.getUsername());

		    return ok(Json.parse(String.format(jsonTemplate, token)));
		}

	    }

	    return badRequest("Error, username and password don't match");

	} catch (

	Exception e) {
	    return internalServerError(e.getMessage());
	}

    }

    public Result findAll(Http.Request request) {

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.userList.render(User.findAll())).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(User.findAll())).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }

    public Result checkToken(Http.Request request) {

	return ok(JwtAuthorizationUtils.validateToken(request.header("Authorization").orElse("")) ? "OK" : "NO");

    }
}
