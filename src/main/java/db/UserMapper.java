package db;

import business.Administrator;
import business.Customer;
import business.Publisher;
import business.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: alexander
 * Date: 5/13/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserMapper {
    private ConnectionManager connectionManager_;

    public UserMapper(ConnectionManager manager) {
        assert(manager != null);

        connectionManager_ = manager;
    }

    public User
    findByLogin(final String login, final int passwordCheck) throws IllegalArgumentException, DataMapperException {
        assert(login != null);

        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "SELECT * from USERS where Login=?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, login);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int password = result.getInt("Password");
                if (password != passwordCheck) {
                    throw new IllegalArgumentException("Incorrect password");
                }
                int id = result.getInt("Id");
                int type = result.getInt("Type");
                String name = result.getString("Name");
                String secondName = result.getString("SecondName");
                String email = result.getString("Email");

                switch (type) {
                    case 0:
                        int discount = result.getInt("PersonalDiscount");
                        return new Customer(id,
                                            login,
                                            password,
                                            name,
                                            secondName,
                                            email,
                                            discount);
                    case 1:
                        return new Administrator(id,
                                                 login,
                                                 password,
                                                 name,
                                                 secondName,
                                                 email);
                    case 2:
                        return new Publisher(id,
                                             login,
                                             password,
                                             name,
                                             secondName,
                                             email);
                    default:
                        throw new DataMapperException("Unknown user type");
                }
            }

            return null;
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while searching for user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void insert(Administrator user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "INSERT into Users VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, 1);
            statement.setString(2, user.getLogin());
            statement.setInt(3, user.getPasswordHash());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSecondName());
            statement.setString(6, user.getEmail());
            statement.setInt(7, 0);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while inserting a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void insert(Publisher user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "INSERT into Users VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, 2);
            statement.setString(2, user.getLogin());
            statement.setInt(3, user.getPasswordHash());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSecondName());
            statement.setString(6, user.getEmail());
            statement.setInt(7, 0);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while inserting a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void insert(Customer user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "INSERT into Users VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, 0);
            statement.setString(2, user.getLogin());
            statement.setInt(3, user.getPasswordHash());
            statement.setString(4, user.getName());
            statement.setString(5, user.getSecondName());
            statement.setString(6, user.getEmail());
            statement.setInt(7, user.getPersonalDiscount().integerValue());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while inserting a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void update(Administrator user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "UPDATE Users SET " +
                           "Password=?, Name=?, SecondName=?, Email=?, PersonalDiscount=? " +
                           "where Login=?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, user.getPasswordHash());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, 0);
            statement.setString(6, user.getLogin());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while updating a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void update(Publisher user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "UPDATE Users SET " +
                    "Password=?, Name=?, SecondName=?, Email=?, PersonalDiscount=? " +
                    "where Login=?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, user.getPasswordHash());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, 0);
            statement.setString(6, user.getLogin());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while updating a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void update(Customer user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "UPDATE Users SET " +
                    "Password=?, Name=?, SecondName=?, Email=?, PersonalDiscount=? " +
                    "where Login=?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, user.getPasswordHash());
            statement.setString(2, user.getName());
            statement.setString(3, user.getSecondName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, user.getPersonalDiscount().integerValue());
            statement.setString(6, user.getLogin());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while updating a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }

    public void delete(User user) throws DataMapperException {
        Connection conn = null;
        try {
            conn = connectionManager_.getConnection();

            String query = "DELETE from Users where login=?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, user.getLogin());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataMapperException("Error occurred while deleting a user", e);
        } finally {
            if (conn != null) {
                try {
                    connectionManager_.closeConnection(conn);
                } catch (SQLException e2) {}
            }
        }
    }
}
