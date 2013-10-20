/**
 * 
 */
package business;

/**
 * @author alexander
 * 
 */
public final class Isbn {
    private final String isbn;
    
    public Isbn(final String isbn) throws WrongFormatException {
        assert (isbn != null);

        String tmp = isbn.replace("-", "");
        if (tmp.length() == 10) {
            tmp = toIsbn13(tmp);
        } else if (tmp.length() != 13) {
            throw new WrongFormatException("Trying to create 13-digit ISBN from non 13-digit number");
        }
        
        if (!isValid(tmp)) {
            throw new WrongFormatException("Invalid 13-digit ISBN number");
        }
        this.isbn = tmp;
    }
    
    private static String toIsbn13(final String isbn) {
        final StringBuilder tmp = new StringBuilder("978");
        tmp.append(isbn.substring(0, 9));
        tmp.append(getLastDigit(tmp.toString()));
        return tmp.toString();
    }

    private static char getLastDigit(final String isbn) {
        int result = 0;
        final char digits[] = isbn.toCharArray();
        final int isbnLength = isbn.length();
        for (int i = 1; i < isbnLength; i += 2) {
            result += digits[i] - '0';
        }
        result *= 3;
        for (int i = 0; i < isbnLength; i += 2) {
            result += digits[i] - '0';
        }
        result %= 10;
        return result == 0 ? '0' : (char) ((10 - result) + '0');
    }

    private static boolean isValid(final String isbn) {
        int check = 0;
        final char digits[] = isbn.toCharArray();
        final int isbnLength = isbn.length();
        for (int i = 1; i < isbnLength; i += 2) {
            check += (digits[i] - '0');
        }
        check *= 3;
        for (int i = 0; i < isbnLength; i += 2) {
            check += (digits[i] - '0');
        }
        return check % 10 == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Isbn isbn1 = (Isbn) o;

        return isbn.equals(isbn1.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return isbn;
    }
}
