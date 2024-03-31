package controllers;

import java.util.List;

import auth.JwtAuthorizationUtils;
import io.ebean.annotation.Transactional;
import models.User;
import models.UserInfo;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;
import utils.PasswordEncryptUtils;

/**
 * The Class AuthController.
 */
public class AuthController extends Controller {

    /**
     * Creates the.
     *
     * @param request the request
     * @return the result
     */
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

    /**
     * Login.
     *
     * @param request the request
     * @return the result
     */
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

    /**
     * Check token.
     *
     * @param request the request
     * @return the result
     */
    public Result checkToken(Http.Request request) {

	return ok(JwtAuthorizationUtils.validateToken(request.header("Authorization").orElse("")) ? "OK" : "NO");

    }

    /**
     * Healthcheck.
     *
     * @param request the request
     * @return the result
     */
    public Result healthcheck(Http.Request request) {

	return ok("HEALTHCHECK OK");
    }

    @Transactional
    public Result delete(Long id, Request request) {

	User user = User.findById(id);

	if (null == user) {
	    return notFound("Error, user not exists");
	}

	UserInfo userInfo = user.getUserInfo();

	userInfo.delete();
	user.delete();

	return ok("User successfully deleted");
    }
}
