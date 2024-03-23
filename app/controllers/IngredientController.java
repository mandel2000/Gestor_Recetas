package controllers;

import java.util.List;

import models.Author;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public class IngredientController extends Controller {

    public Result create(Http.Request request) {

	Author createdAuthor = Author.fromJson(request.body().asJson());

	if (Author.findByNameAndSurname(createdAuthor.getName(), createdAuthor.getSurname()).isEmpty()) {
	    createdAuthor.save();

	    if (request.accepts("application/xml")) {

		return Results.created(views.xml.authorXml.render(createdAuthor)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.created(createdAuthor.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }
	} else {
	    return badRequest("Error, author already exists");
	}

    }

    public Result update(Http.Request request) {

	Author authorToUpdate = Author.fromJson(request.body().asJson());

	if (Author.findById(authorToUpdate.getId()) != null) {

	    authorToUpdate.update();

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.authorXml.render(authorToUpdate)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return ok(authorToUpdate.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return badRequest("Error, author not found");
	}
    }

    public Result getAll(Http.Request request) {

	if (request.accepts("application/xml")) {

	    return Results.ok(views.xml.authorList.render(Author.findAll())).as("application/xml");

	} else if (request.accepts("application/json")) {

	    return Results.ok(play.libs.Json.toJson(Author.findAll())).as("application/json");

	} else {

	    return badRequest("Unsupported format");
	}
    }

    public Result getById(Long id, Http.Request request) {

	Author author = Author.findById(id);

	if (author != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.authorXml.render(author)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(author.asJson()).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

    public Result getByName(String name, Http.Request request) {

	List<Author> authors = Author.findByName(name);

	if (authors != null) {

	    if (request.accepts("application/xml")) {

		return Results.ok(views.xml.authorList.render(authors)).as("application/xml");

	    } else if (request.accepts("application/json")) {

		return Results.ok(play.libs.Json.toJson(Author.findAll())).as("application/json");

	    } else {

		return badRequest("Unsupported format");
	    }

	} else {
	    return Results.noContent();
	}
    }

}
