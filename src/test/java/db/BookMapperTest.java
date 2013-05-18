package db;

import business.*;
import org.junit.*;

import java.sql.*;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: alexander
 * Date: 5/14/13
 * Time: 9:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class BookMapperTest {
    private static int publisherId_;
    public static final double EPSILON = 1e-15;

    @BeforeClass
    public static void setUpDatabase() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement = null;
        PreparedStatement prepStatement = null;
        Connection connection = null;
        ResultSet keys = null;
        try {
            connection = manager.getConnection();
            String query = "INSERT into Users(Type, Login, Password, Name, SecondName, Email, PersonalDiscount)" +
                           "VALUES (2, 'foo', 0, 'Mad', 'Jack', 'The pirate', 0)";
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);

            keys = statement.getGeneratedKeys();
            if (keys.next()) {
                publisherId_ = keys.getInt(1);
            } else {
                throw new Exception("Can't get publisher id");
            }

            query = "INSERT into Books(Name, Genre, Isbn, PublicationDate, Price, Discount, NumSold, PublisherId)" +
                    "VALUES ('foo', 'bar', '9783161484100', '2012-01-01', 200, 10, 0, ?)";
            prepStatement = connection.prepareStatement(query);
            prepStatement.setInt(1, publisherId_);
            prepStatement.executeUpdate();
        } finally {
            if (connection != null) connection.close();
            if (prepStatement != null) prepStatement.close();
            if (statement != null) statement.close();
            if (keys != null) keys.close();
        }
    }

    @AfterClass
    public static void clearDatabase() throws SQLException {
        final TestConnectionManager manager = new TestConnectionManager();
        Statement statement1 = null;
        Statement statement2 = null;
        Connection connection = null;
        try {
            connection = manager.getConnection();
            final String query1 = "DELETE from Books";
            statement1 = connection.createStatement();
            statement1.executeUpdate(query1);
            final String query2 = "DELETE from Users";
            statement2 = connection.createStatement();
            statement2.executeUpdate(query2);
        } finally {
            if (connection != null) connection.close();
            if (statement1 != null) statement1.close();
            if (statement2 != null) statement2.close();
        }
    }

    @Test
    public void selectTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            final BookMapper mapper = new BookMapper(connection);
            final List<Book> books = mapper.findByName("foo");
            assertEquals("Found incorrect number of books", 1, books.size());
            for (final Book book : books) {
                assertEquals("Wrong name", "foo", book.getName());
                assertEquals("Wrong genre", "bar", book.getGenre());
                assertEquals("Wrong isbn", "9783161484100", book.getIsbn().toString());
                assertEquals("Wrong price", 180, book.getPrice(), EPSILON);
            }
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Test
    public void insertTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            final UserMapper userMapper = new UserMapper(connection);
            final User publisher = userMapper.findById(publisherId_);
            assertNotNull("Publisher not found", publisher);
            assertThat("Retrieved wrong user type", publisher, instanceOf(Publisher.class));

            final Book book = new Book.Builder(-1,
                                               "test",
                                               "",
                                               (Publisher)publisher,
                                               new GregorianCalendar(),
                                               new Isbn10("097522980X").toIsbn13(),
                                               120.44).build();
            final BookMapper bookMapper = new BookMapper(connection);
            final int id = bookMapper.insert(book);
            final Book check = bookMapper.findById(id);
            assertNotNull("Inserted book not found", check);
            assertEquals("Wrong name of inserted book", "test", book.getName());
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Test
    public void updateTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            final UserMapper userMapper = new UserMapper(connection);
            final User publisher = userMapper.findById(publisherId_);
            assertNotNull("Publisher not found", publisher);
            assertThat("Retrieved wrong user type", publisher, instanceOf(Publisher.class));

            final Book book = new Book.Builder(-1,
                                               "test",
                                               "",
                                               (Publisher)publisher,
                                               new GregorianCalendar(),
                                               new Isbn10("9992158107").toIsbn13(),
                                               120.44).build();
            final BookMapper bookMapper = new BookMapper(connection);
            final int id = bookMapper.insert(book);
            final Book test = new Book.Builder(id,
                                               "test69",
                                               "horror",
                                               (Publisher)publisher,
                                               new GregorianCalendar(),
                                               new Isbn10("9992158107").toIsbn13(),
                                               120.44).build();
            bookMapper.update(test);
            final Book check = bookMapper.findById(id);
            assertNotNull("Updated book not found", check);
            assertEquals("Wrong name of updated book", "test69", check.getName());
            assertEquals("Wrong genre of updated book", "horror", check.getGenre());
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Test
    public void deleteTest() throws Exception {
        final TestConnectionManager manager = new TestConnectionManager();
        Connection connection = null;
        try {
            connection = manager.getConnection();
            final UserMapper userMapper = new UserMapper(connection);
            final User publisher = userMapper.findById(publisherId_);
            assertNotNull("Publisher not found", publisher);
            assertThat("Retrieved wrong user type", publisher, instanceOf(Publisher.class));

            final Book book = new Book.Builder(-1,
                                               "test",
                                               "",
                                               (Publisher)publisher,
                                               new GregorianCalendar(),
                                               new Isbn10("0123456789").toIsbn13(),
                                               120.44).build();
            final BookMapper bookMapper = new BookMapper(connection);
            final int id = bookMapper.insert(book);

            bookMapper.delete(id + 1);
            assertNotNull("Book was deleted", bookMapper.findById(id));
            bookMapper.delete(id);
            assertNull("Book was not deleted", bookMapper.findById(id));
        } finally {
            if (connection != null) connection.close();
        }
    }
}