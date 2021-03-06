/**
 * 
 */
package edu.service;

import edu.business.Book;
import edu.business.Isbn;
import edu.Main;
import edu.db.BookMapper;
import edu.db.ConnectionManager;
import edu.db.DataMapperException;
import edu.db.DerbyConnectionManager;
import edu.exception.EntryNotFoundException;
import edu.exception.EntryRedefinitionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @author alexander
 * 
 */
final class BookCatalogue {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void createBook(final Book book, final int amount)
            throws EntryRedefinitionException {
        assert book != null;
        final ConnectionManager manager = new DerbyConnectionManager();
        try (Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            final Book test = mapper.find(book.getIsbn());
            if (test != null) throw new EntryRedefinitionException("Book already exists");
            mapper.insert(book, amount);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    public static List<Book> getBooks(final String search) {
        assert search != null;
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            
            // first trying to find the book by ISBN
            if (Isbn.isValid(search)) {
                final Isbn isbn = new Isbn(search);
                final List<Book> result = new LinkedList<>();
                final Book book = mapper.find(isbn);
                if (book != null) {
                    result.add(book);
                }
                return result;
            }

            return mapper.find(search);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
    public static Book getBook(final String isbn) {
        assert isbn != null;
        
        final Isbn parsedIsbn = new Isbn(isbn);
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            return mapper.find(parsedIsbn);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
    public static int getAmount(final Book book) throws EntryNotFoundException {
        assert book != null;
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            final int result = mapper.getAmount(book.getId());
            if (result == -1) throw new EntryNotFoundException("Book not found");
            return result;
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
    public static void setAmount(final Book book, final int amount) {
        assert book != null;
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            mapper.setAmount(book.getId(), amount);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
    public static void deleteBook(final Book book) {
        assert book != null;
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            mapper.delete(book.getId());
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
    public static void updateBook(final Book book) {
        assert book != null;
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            mapper.update(book);
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
    public static int getNumSold(final Book book) throws EntryNotFoundException {
        assert book != null;
        
        final ConnectionManager manager = new DerbyConnectionManager();
        try (final Connection connection = manager.getConnection("db")) {
            final BookMapper mapper = new BookMapper(connection);
            final int result = mapper.getNumSold(book.getId());
            if (result == -1) {
                throw new EntryNotFoundException("Book not found");
            }
            return result;
        } catch (SQLException | DataMapperException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    private BookCatalogue() {}
}
