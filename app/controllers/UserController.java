package controllers;

import javax.inject.Inject;

import io.ebean.annotation.Transactional;
import models.User;
import models.UserInfo;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.Request;
import play.mvc.Result;
import play.mvc.Results;

/**
 * The Class UserController.
 */
public class UserController extends Controller {

    /** The form factory. */
    private final FormFactory formFactory;

    /**
     * Instantiates a new user controller.
     *
     * @param formFactory the form factory
     */
    @Inject
    public UserController(FormFactory formFactory) {
	this.formFactory = formFactory;
    }

    /**
     * Adds the user info.
     *
     * @param id      the id
     * @param request the request
     * @return the result
     */
    @Transactional
    public Result addUserInfo(Long id, Request request) {

	User user = User.findById(id);

	if (null != user) {
	    Form<UserInfo> form = formFactory.form(UserInfo.class).bindFromRequest(request);

	    if (form.hasErrors()) {
		return badRequest(form.errorsAsJson());
	    }

	    UserInfo userInfo = form.get();
	    userInfo.save();
	    user.setUserInfo(userInfo);
	    user.save();

	    return created(userInfo.asJson());

	}

	return badRequest("Error user not found");
    }

    /**
     * Update user info.
     *
     * @param id      the id
     * @param request the request
     * @return the result
     */
    @Transactional
    public Result updateUserInfo(Long id, Request request) {

	Form<UserInfo> form = formFactory.form(UserInfo.class).bindFromRequest(request);
	if (form.hasErrors()) {
	    return badRequest(form.errorsAsJson());
	}

	UserInfo userInfo = UserInfo.findById(form.get().getId());

	if (null != userInfo) {

	    userInfo.save();
	    User user = userInfo.getUser();
	    user.setUserInfo(userInfo);
	    user.save();

	    return ok(userInfo.asJson());

	}

	return badRequest("Error user not found");
    }

    /**
     * Find all.
     *
     * @param request the request
     * @return the result
     */
    public Result findAll(Http.Request request) {

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.userList.render(User.findAll())).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(User.findAll())).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }

    /**
     * Fin by id.
     *
     * @param id      the id
     * @param request the request
     * @return the result
     */
    public Result findById(Long id, Http.Request request) {

	User user = User.findById(id);

	if (null == user) {
	    return notFound("Error, user not exists");
	}

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.userXml.render(user)).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(user)).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }
}
