package controllers;

import java.util.List;

import action.AuthAction;
import models.Ingredient;
import play.filters.csrf.AddCSRFToken;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.With;

/**
 * The Class IngredientController.
 */
@With(AuthAction.class)
@AddCSRFToken
public class IngredientController extends Controller {

    /**
     * Creates the.
     *
     * @param request the request
     * @return the result
     */
    public Result create(Http.Request request) {

	Ingredient createdIngredient = Ingredient.fromJson(request.body().asJson());

	if (Ingredient.findByName(createdIngredient.getName()).isEmpty()) {
	    createdIngredient.save();

	    if (request.accepts("application/xml")) {

		return Results.created(views.xml.ingredientXml.render(createdIngredient)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.created(createdIngredient.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }
	} else {
	    return badRequest("Error, Ingredient already exists");
	}

    }

    /**
     * Update.
     *
     * @param request the request
     * @return the result
     */
    public Result update(Http.Request request) {

	Ingredient ingredientToUpdate = Ingredient.fromJson(request.body().asJson());

	if (Ingredient.findById(ingredientToUpdate.getId()) != null) {

	    ingredientToUpdate.update();

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.ingredientXml.render(ingredientToUpdate)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return ok(ingredientToUpdate.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return badRequest("Error, Ingredient not found");
	}
    }

    /**
     * Gets the all.
     *
     * @param request the request
     * @return the all
     */
    public Result getAll(Http.Request request) {

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.ingredientList.render(Ingredient.findAll())).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(Ingredient.findAll())).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }

    /**
     * Gets the by id.
     *
     * @param id the id
     * @param request the request
     * @return the by id
     */
    public Result getById(Long id, Http.Request request) {

	Ingredient ingredient = Ingredient.findById(id);

	if (ingredient != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.ingredientXml.render(ingredient)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(ingredient.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

    /**
     * Gets the by name.
     *
     * @param name the name
     * @param request the request
     * @return the by name
     */
    public Result getByName(String name, Http.Request request) {

	List<Ingredient> ingredients = Ingredient.findByName(name);

	if (ingredients != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.ingredientList.render(ingredients)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(play.libs.Json.toJson(ingredients)).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

}
