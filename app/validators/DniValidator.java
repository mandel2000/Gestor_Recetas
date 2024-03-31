package validators;

import play.data.validation.Constraints;
import play.libs.F;
import play.libs.F.Tuple;

public class DniValidator extends Constraints.Validator<String> {

    @Override
    public Tuple<String, Object[]> getErrorMessageKey() {
	return new F.Tuple<String, Object[]>("DNI not valid", new Object[] { "" });
    }

    @Override
    public boolean isValid(String dni) {

	if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
	    return false;
	}

	String letter = (dni.substring(8)).toUpperCase();

	try {

	    return validDniLetter(dni, letter);

	} catch (NumberFormatException excepcion) {
	    return false;
	}

    }

    private boolean validDniLetter(String dni, String letra) throws NumberFormatException {

	int dniAsInt = Integer.parseInt(dni.substring(0, 8));

	int remainder = 0;
	String[] lettersCatalog = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
		"V", "H", "L", "C", "K", "E" };

	remainder = dniAsInt % 23;

	return letra.equals(lettersCatalog[remainder]);

    }

}
