package controllers;

import java.util.List;

import models.Ingredient;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public class IngredientController extends Controller {

    public Result create(Http.Request request) {

	Ingredient createdIngredient = Ingredient.fromJson(request.body().asJson());

	if (Ingredient.findByName(createdIngredient.getName()).isEmpty()) {
	    createdIngredient.save();

	    if (request.accepts("application/xml")) {

		return Results.created(views.xml.IngredientXml.render(createdIngredient)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.created(createdIngredient.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }
	} else {
	    return badRequest("Error, Ingredient already exists");
	}

    }

    public Result update(Http.Request request) {

	Ingredient ingredientToUpdate = Ingredient.fromJson(request.body().asJson());

	if (Ingredient.findById(ingredientToUpdate.getId()) != null) {

	    ingredientToUpdate.update();

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.IngredientXml.render(ingredientToUpdate)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return ok(ingredientToUpdate.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return badRequest("Error, Ingredient not found");
	}
    }

    public Result getAll(Http.Request request) {

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.IngredientList.render(Ingredient.findAll())).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(Ingredient.findAll())).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }

    public Result getById(Long id, Http.Request request) {

	Ingredient ingredient = Ingredient.findById(id);

	if (ingredient != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.IngredientXml.render(ingredient)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(ingredient.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

    public Result getByName(String name, Http.Request request) {

	List<Ingredient> ingredients = Ingredient.findByName(name);

	if (ingredients != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.IngredientList.render(ingredients)).as("application/xml");

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
