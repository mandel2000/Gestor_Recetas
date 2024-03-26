package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * The Class PasswordEncryptUtils.
 */
public class PasswordEncryptUtils {

    /**
     * Encrypt.
     *
     * @param password the password
     * @return the string
     */
    public static String encrypt(String password) {
	try {
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");

	    byte[] hashedBytes = digest.digest(password.getBytes());

	    StringBuilder sb = new StringBuilder();
	    for (byte b : hashedBytes) {
		sb.append(String.format("%02x", b));
	    }

	    return sb.toString();

	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	    return null;
	}
    }

    /**
     * Check password.
     *
     * @param plainPassword the plain password
     * @param hashedPassword the hashed password
     * @return true, if successful
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {

	return encrypt(plainPassword).equals(hashedPassword);
    }
}
