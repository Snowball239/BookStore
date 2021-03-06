/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.controllers;

import edu.data.BookOrder;
import edu.data.BookOrderEntry;
import edu.data.Stock;
import edu.data.User;
import edu.ejb.OrderEjb;
import edu.ejb.StockEjb;
import edu.ejb.UserEjb;
import edu.util.MessageManager;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author alexander
 */
@Model
public class OrderController {
    @EJB
    private OrderEjb oe;
    @EJB
    private StockEjb stock;
    @EJB
    private UserEjb ue;
    
    @Inject
    private CartController cc;
    @Inject
    private AuthController ac;
    
    private BookOrder order = new BookOrder();
    
    public String createOrder() {
        final List<BookOrderEntry> cart = cc.getCart();
        if (cart.isEmpty()) {
            MessageManager.error("Your cart is empty!");
            return "";
        }
        for (final BookOrderEntry entry : cart) {
            final Stock s = stock.findByBook(entry.getBook());
            s.setAmount(s.getAmount() - entry.getAmount());
            stock.update(s);
        }
        
        order = new BookOrder(cart);
        order = oe.create(order);

        final User user = ac.getUser();
        user.addOrder(order);
        ac.setUser(ue.update(user));
        
        cc.emptyCart();
        return "/user_pages/home?success=true&faces-redirect=true";
    }
    
    public List<BookOrder> getOrders() {
        return ac.getUser().getOrders();
    } 
    
    public void setOrder(final BookOrder order) {
        this.order = order;
    }
    
    public List<BookOrderEntry> getOrderEntries() {
        return order.getEntries();
    }
}
