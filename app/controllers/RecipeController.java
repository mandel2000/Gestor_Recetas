package controllers;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import action.AuthAction;
import io.ebean.annotation.Transactional;
import models.Author;
import models.Ingredient;
import models.Recipe;
import play.filters.csrf.AddCSRFToken;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.With;

@With(AuthAction.class)
@AddCSRFToken
public class RecipeController extends Controller {

    @Transactional
    public Result create(Http.Request request) {

	Recipe createdRecipe = Recipe.fromJson(request.body().asJson());

	if (Recipe.findByTitle(createdRecipe.getTitle()).isEmpty()) {

	    JsonNode authorJsonNode = request.body().asJson().get("author");

	    if (null != authorJsonNode) {

		Author authorToCreate = Author.fromJson(authorJsonNode);

		createdRecipe.setAuthor(getRecipeAuthor(authorToCreate));
	    }

	    JsonNode ingredientsJsonNode = request.body().asJson().get("ingredients");

	    if (null != ingredientsJsonNode) {
		List<Ingredient> jsonIngredients = Ingredient.listFromJson(ingredientsJsonNode);
		List<Ingredient> recipeIngredients = new ArrayList<>();

		jsonIngredients.forEach((ingredient) -> {

		    List<Ingredient> temp = Ingredient.findByName(ingredient.getName());
		    if (temp.isEmpty()) {
			ingredient.save();
			recipeIngredients.add(ingredient);
		    } else {
			recipeIngredients.add(temp.get(0));
		    }

		});

		createdRecipe.setIngredients(recipeIngredients);
	    }

	    createdRecipe.save();

	    if (request.accepts("application/xml")) {

		return Results.created(views.xml.recipeXml.render(createdRecipe)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.created(createdRecipe.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }
	} else {
	    return badRequest("Error, Recipe already exists");
	}

    }

    private Author getRecipeAuthor(Author author) {

	List<Author> tempAuthor = Author.findByNameAndSurname(author.getName(), author.getSurname());
	if (tempAuthor.isEmpty()) {
	    author.save();
	    return author;
	}

	return tempAuthor.get(0);

    }

    public Result update(Http.Request request) {

	Recipe recipeToUpdate = Recipe.fromJson(request.body().asJson());

	if (Recipe.findById(recipeToUpdate.getId()) != null) {

	    recipeToUpdate.update();

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.recipeXml.render(recipeToUpdate)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return ok(recipeToUpdate.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return badRequest("Error, Recipe not found");
	}
    }

    public Result getAll(Http.Request request) {

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.recipeList.render(Recipe.findAll())).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(Recipe.findAll())).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }

    public Result getById(Long id, Http.Request request) {

	Recipe recipe = Recipe.findById(id);

	if (recipe != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.recipeXml.render(recipe)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(recipe.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

    public Result getByTitle(String title, Http.Request request) {

	List<Recipe> recipes = Recipe.findByTitle(title);

	if (recipes != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.recipeList.render(recipes)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(play.libs.Json.toJson(recipes)).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

}
