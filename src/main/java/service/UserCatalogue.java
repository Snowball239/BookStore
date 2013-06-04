package service;

import business.BookStore;
import business.Customer;
import business.User;
import db.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: alexander
 * Date: 5/26/13
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public final class UserCatalogue {
    private static final Logger LOGGER = Logger.getLogger(BookStore.class.getName());

    public static User getUser(final String login, final String password) throws EntryNotFoundException, IncorrectPasswordException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            final User result = mapper.find(login);

            if (result == null)
                throw new EntryNotFoundException("user not found");
            if (password.hashCode() != result.getPasswordHash())
                throw new IncorrectPasswordException("incorrect password");

            return result;
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }
    
    public static void createUser(final String login, 
                                  final String password,
                                  final String name,
                                  final String secondName,
                                  final String email) throws EntryRedefinitionException {
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final UserMapper mapper = new UserMapper(connection);
            
            final User test = mapper.find(login);
            if (test != null) throw new EntryRedefinitionException("user already exists");
            
            final User user = new Customer(-1, login, password.hashCode(), name, secondName, email);
            mapper.insert(user);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }

    private UserCatalogue() {}
}