package business;

import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public final class BookTest {
    public static final double EPSILON = 1e-15;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testBookGetPrice1() throws WrongFormatException {
        Book tester = new Book.Builder("",
                                       "",
                                       "",
                                       new Publisher(0, ""),
                                       new GregorianCalendar(),
                                       new Isbn("9783161484100"),
                                       120.44).build();
        assertEquals("Incorrect price", 120.44, tester.getPrice(), EPSILON);
    }

    @Test
    public void testBookGetPrice2() throws WrongFormatException {
        Book tester = new Book.Builder("",
                                       "",
                                       "",
                                       new Publisher(0, ""),
                                       new GregorianCalendar(),
                                       new Isbn("9783161484100"),
                                       120.44).discount(100).build();
        assertEquals("Incorrect price", 0, tester.getPrice(), EPSILON);
    }

    @Test
    public void testBookGetPrice3() throws WrongFormatException {
        Book tester = new Book.Builder("",
                                       "",
                                       "",
                                       new Publisher(0, ""),
                                       new GregorianCalendar(),
                                       new Isbn("9783161484100"),
                                       120.44).discount(48).build();
        assertEquals("Incorrect price", 120.44 * 0.52, tester.getPrice(), EPSILON);
    }

    @Test
    public void testBookIllegalArg() throws WrongFormatException {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Illegal discount settings");
        @SuppressWarnings("unused")
        Book tester = new Book.Builder("",
                                       "",
                                       "",
                                       new Publisher(0, ""),
                                       new GregorianCalendar(),
                                       new Isbn("9783161484100"),
                                       120.44).discount(-1).build();
    }
}
