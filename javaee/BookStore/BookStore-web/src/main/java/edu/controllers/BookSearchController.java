/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.controllers;

import edu.data.Book;
import edu.ejb.BookEjb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author alexander
 */
@Model
public class BookSearchController {
    @EJB
    private BookEjb be;
    
    @Inject
    private Logger logger;
    
    private String searchStr;

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    
    public List<Book> search() {
        logger.log(Level.INFO, "searching for {0}", searchStr);
        return be.fuzzyFind(searchStr == null ? "" : searchStr);
    }
}
