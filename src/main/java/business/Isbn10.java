/**
 * 
 */
package business;

/**
 * @author alexander
 *
 */
public class Isbn10 extends Isbn {
	public Isbn10(final String isbn) throws IllegalArgumentException {
		super(isbn);
		if (isbn.length() != 10) {
			throw new IllegalArgumentException("Trying to create 10-digit ISBN from non 10-digit number");
		}
		if (!isValid(isbn)) {
			throw new IllegalArgumentException("Invalid 10-digit ISBN number");
		}
		isbn_ = isbn;
	}
	
	@Override
	protected boolean isValid(final String isbn) {
		int check = 0;
		char digits[] = isbn.toCharArray();
	    for (int i = 10; i > 1; i--) {
	        check += i * (digits[10 - i] - '0');
	    }
	    if (digits[9] == 'X') {
	    	check += 10;
	    } else {
	    	check += (digits[9] - '0');
	    }
	    return check % 11 == 0;
	}
}