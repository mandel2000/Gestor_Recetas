package action;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import auth.JwtAuthorizationUtils;
import play.mvc.Action.Simple;
import play.mvc.Http.Request;
import play.mvc.Result;

/**
 * The Class AuthAction.
 */
public class AuthAction extends Simple {

    /**
     * Call.
     *
     * @param req the req
     * @return the completion stage
     */
    @Override
    public CompletionStage<Result> call(Request req) {

	String authHeader = req.header("Authorization").orElse("");

	if (authHeader != null && JwtAuthorizationUtils.validateToken(authHeader)) {
	    return delegate.call(req);
	} else {
	    return CompletableFuture.completedFuture(unauthorized("No estás autenticado"));
	}
    }

}
