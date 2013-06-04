/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import business.Book;
import business.Customer;
import service.BookCatalogue;
import service.EntryNotFoundException;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author alexander
 */
public class BookSearchEntryPanel extends javax.swing.JPanel {

    /**
     * Creates new form BookSearchEntryPanel
     */
    public BookSearchEntryPanel(final Book book, final Customer user) {
        assert (book != null);
        assert (user != null);
        
        book_ = book;
        user_ = user;
        try {
            amount_ = BookCatalogue.getAmount(book);
        } catch (EntryNotFoundException e) {
            //unreachable
            assert false;
        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel bookNameLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel isbnLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel pubLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel pubDateLabel = new javax.swing.JLabel();
        javax.swing.JButton buyButton = new javax.swing.JButton();
        javax.swing.JLabel priceLabel = new javax.swing.JLabel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel authorLabel = new javax.swing.JLabel();
        javax.swing.JLabel avaliableLabel = new javax.swing.JLabel();

        bookNameLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        bookNameLabel.setText(book_.getName());

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("ISBN:");

        isbnLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        isbnLabel.setText(book_.getIsbn().toString());

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Publisher:");

        pubLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        pubLabel.setText(book_.getPublisher().getName());

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Date:");

        pubDateLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        final GregorianCalendar date = book_.getPublicationDate();
        int year = date.get(GregorianCalendar.YEAR);
        int month = date.get(GregorianCalendar.MONTH);
        int day = date.get(GregorianCalendar.DAY_OF_MONTH);
        pubDateLabel.setText(year + "-" + month + "-" + day);

        buyButton.setText("Buy");
        if (amount_ == 0) {
            buyButton.setEnabled(false);
        }
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        priceLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        priceLabel.setText("$" + book_.getPrice());

        jLabel1.setText("Author:");

        authorLabel.setText(book_.getAuthor());

        avaliableLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        avaliableLabel.setForeground(new java.awt.Color(255, 0, 51));
        avaliableLabel.setText("Not avaliable");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bookNameLabel)
                        .addGap(47, 153, Short.MAX_VALUE)
                        .addComponent(priceLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(isbnLabel)
                                    .addComponent(authorLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(avaliableLabel)
                                    .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pubLabel)
                                    .addComponent(pubDateLabel))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookNameLabel)
                    .addComponent(priceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(authorLabel)
                    .addComponent(avaliableLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(isbnLabel)
                    .addComponent(buyButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pubLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pubDateLabel)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        if (amount_ != 0) {
            avaliableLabel.setVisible(false);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        final JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        parent.dispose();
        new BuyFrame(book_, user_).setVisible(true);
    }//GEN-LAST:event_buyButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private final Book book_;
    private final Customer user_;
    private int amount_ = 0;
}
