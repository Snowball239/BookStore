/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gui;

import edu.ServiceWrapper;
import edu.rmi.BookStoreService;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexander
 */
@SuppressWarnings("serial")
public final class BookInfoPanel extends JPanel {
    private static final Logger LOGGER = Logger.getLogger(BookInfoPanel.class.getName());

    /**
     * Creates new form BookInfoPanel
     */
    public BookInfoPanel() {
        initComponents();
    }

    public String getAmount() {
        return amountField.getText();
    }

    public String getAuthor() {
        return authorField.getText();
    }

    public DateInputPanel getDateInputPanel() {
        return dateInputPanel;
    }

    public String getGenre() {
        return genreField.getText();
    }

    public String getIsbn() {
        return isbnField.getText();
    }

    public String getBookName() {
        return nameField.getText();
    }

    public String getPrice() {
        return priceField.getText();
    }

    public String getPub() {
        return (String) pubComboBox.getSelectedItem();
    }
    
    public void setAmount(final String amount) {
        amountField.setText(amount);
    }
    
    public void setAuthor(final String author) {
        authorField.setText(author);
    }
    
    public void setGenre(final String genre) {
        genreField.setText(genre);
    }
    
    public void setIsbn(final String isbn) {
        isbnField.setText(isbn);
    }
    
    public void setBookName(final String name) {
        nameField.setText(name);
    }
    
    public void setPrice(final String price) {
        priceField.setText(price);
    }
    
    public void setPub(final String pub) {
        pubComboBox.setSelectedItem(pub);
    }
    
    public void setDate(int day, int month, int year) {
        dateInputPanel.setDay(day);
        dateInputPanel.setMonth(month);
        dateInputPanel.setYear(year);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel6 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel7 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel8 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        authorField = new javax.swing.JTextField();
        genreField = new javax.swing.JTextField();
        isbnField = new javax.swing.JTextField();
        priceField = new javax.swing.JFormattedTextField();
        amountField = new javax.swing.JTextField();
        pubComboBox = new javax.swing.JComboBox<String>();

        jLabel1.setText("Name:");
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText("Author:");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setText("Genre:");
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText("Publisher:");
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText("Publication Date:");
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText("ISBN:");
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setText("Price:");
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText("Amount:");
        jLabel8.setName("jLabel8"); // NOI18N

        nameField.setName("nameField"); // NOI18N

        authorField.setName("authorField"); // NOI18N

        genreField.setName("genreField"); // NOI18N

        isbnField.setName("isbnField"); // NOI18N

        priceField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        priceField.setName("priceField"); // NOI18N

        amountField.setName("amountField"); // NOI18N

        try {
            final List<String> publisherNames = service.getPublisherNames();
            pubComboBox.setModel(new DefaultComboBoxModel<>(publisherNames.toArray(new String[publisherNames.size()])));
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "RMI error! See the log for the details", "Error", JOptionPane.ERROR_MESSAGE);
            LOGGER.log(Level.SEVERE, null, e);
        }
        pubComboBox.setName("pubComboBox"); // NOI18N

        dateInputPanel.setName("dateInputPanel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(genreField)
                    .addComponent(authorField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nameField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(isbnField)
                    .addComponent(priceField)
                    .addComponent(amountField)
                    .addComponent(pubComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(authorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(genreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pubComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(dateInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(isbnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(109, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountField;
    private javax.swing.JTextField authorField;
    private final edu.gui.DateInputPanel dateInputPanel = new edu.gui.DateInputPanel();
    private javax.swing.JTextField genreField;
    private javax.swing.JTextField isbnField;
    private javax.swing.JTextField nameField;
    private javax.swing.JFormattedTextField priceField;
    private javax.swing.JComboBox<String> pubComboBox;
    // End of variables declaration//GEN-END:variables
    private final BookStoreService service = ServiceWrapper.getBookStoreService();
}