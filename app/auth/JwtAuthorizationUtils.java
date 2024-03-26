package auth;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationUtils {

    private static final String SECRET_KEY = "a838b06b0a431b8cb6e7e576ee5a91cbfb9d535b69f391d6950620aec03434d1";

    public static String generateToken(String username) {

	Date now = new Date();
	Date expiryDate = new Date(now.getTime() + 3600000);
	return Jwts.builder().setSubject(username).setIssuedAt(now).signWith(getKey()).setExpiration(expiryDate)
		.compact();
    }

    public static boolean validateToken(String token) {
	try {
	    Claims claims = Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

//	    if (claims.getExpiration().before(new Date())) {
//		return false;
//	    } else {
//		return true;
//	    }

	    return true;

	} catch (Exception e) {
	    return false;
	}
    }

    private static Key getKey() {
	return new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA512");
    }
}
