package business;

import java.util.GregorianCalendar;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class OrderEntryTest {
    private Book book;
    public static final double EPSILON = 1e-15;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void init() throws WrongFormatException {
        book = new Book.Builder("",
                                "",
                                "",
                                new Publisher(0, ""),
                                new GregorianCalendar(),
                                new Isbn13("9783161484100"),
                                120.44).discount(50).build();
    }

    @Test
    public void testOrderEntryGetPrice1() {
        final OrderEntry tester = new OrderEntry(0, book, 5);
        assertEquals("Incorrect entry price",
                     120.44 * 0.5 * 5,
                     tester.getPrice(),
                     EPSILON);
    }
}
