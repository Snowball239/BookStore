/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client.gui;

import business.Publisher;
import client.ServiceWrapper;
import rmi.BookStoreService;
import service.EntryNotFoundException;

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
public class PublisherChooserFrame extends JFrame {
    private static final Logger LOGGER = Logger.getLogger("BookStore");

    /**
     * Creates new form PublisherChooserFrame
     */
    public PublisherChooserFrame() {
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

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        pubList = new javax.swing.JList<String>();
        javax.swing.JButton modifyButton = new javax.swing.JButton();
        javax.swing.JButton backButton = new javax.swing.JButton();
        javax.swing.JButton deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        final String[] publishers;
        try {
            final List<String> publisherNames = service.getPublisherNames();
            publishers = publisherNames.toArray(new String[publisherNames.size()]);
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new IllegalStateException();
        }
        pubList.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() { return publishers.length; }
            @Override
            public String getElementAt(int i) { return publishers[i]; }
        });
        pubList.setName("pubList"); // NOI18N
        jScrollPane1.setViewportView(pubList);

        modifyButton.setText("Modify");
        modifyButton.setName("modifyButton"); // NOI18N
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        backButton.setText("back");
        backButton.setName("backButton"); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.setName("deleteButton"); // NOI18N
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modifyButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(modifyButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("unchecked")
    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        final String newName = JOptionPane.showInputDialog(this, "Type new name");
        if (newName == null) return;
        if (newName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name field is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final String[] publishers;
        final BookStoreService service = ServiceWrapper.getBookStoreService();
        try {
            final Publisher pub = service.getPublisher(pubList.getSelectedValue());
            pub.setName(newName);
            service.updatePublisher(pub);
            final List<String> publisherNames = service.getPublisherNames();
            publishers = publisherNames.toArray(new String[publisherNames.size()]);
        } catch (EntryNotFoundException | RemoteException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new IllegalStateException();
        }
        JOptionPane.showMessageDialog(this, "Publisher updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        pubList.setModel(new javax.swing.AbstractListModel<String>() {
            @Override
            public int getSize() { return publishers.length; }
            @Override
            public String getElementAt(int i) { return publishers[i]; }
        });
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
        new MainAdminFrame().setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    @SuppressWarnings("unchecked")
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            final String name = pubList.getSelectedValue();
            service.deletePublisher(service.getPublisher(name));
        } catch (EntryNotFoundException | RemoteException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new IllegalStateException();
        }
        JOptionPane.showMessageDialog(this, "Publisher deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        final List<String> publisherNames;
        try {
            publisherNames = service.getPublisherNames();
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, null, e);
            throw new IllegalStateException();
        }

        pubList.setModel(new AbstractListModel<String>() {
            private final String[] publishers = publisherNames.toArray(new String[publisherNames.size()]);
            @Override
            public int getSize() { return publishers.length; }
            @Override
            public String getElementAt(int i) { return publishers[i]; }
        });
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> pubList;
    // End of variables declaration//GEN-END:variables
    private final BookStoreService service = ServiceWrapper.getBookStoreService();
}
