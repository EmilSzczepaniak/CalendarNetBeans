/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;
import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Emil
 */
public class Application extends javax.swing.JFrame {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    /**
     * Creates new form Application
     */
    public Application() {
        initComponents();
        conn = DBConnect.getConnection();
        Update_table();
        
    }
    private void Update_table(){
        try{
        String sql = "SELECT* FROM data ";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery(sql);
        tblDataBaseData.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void Update_table1(){
        try{
        String sql = "SELECT* FROM data WHERE data_urodzenia = 1995 ";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery(sql);
        tblDataBaseData.setModel(DbUtils.resultSetToTableModel(rs));
        
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAddEvent = new javax.swing.JButton();
        btnCancelEvent = new javax.swing.JButton();
        btnDeleteEvent = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        taEvent = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDataBaseData = new javax.swing.JTable();
        dpDate = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        spinerTime = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCalendar = new javax.swing.JMenu();
        menuCalendar1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAddEvent.setText("Dodaj");
        btnAddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEventActionPerformed(evt);
            }
        });

        btnCancelEvent.setText("Anuluj");

        btnDeleteEvent.setText("Usuń");
        btnDeleteEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEventActionPerformed(evt);
            }
        });

        btnExit.setText("Wyjdź");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        taEvent.setColumns(20);
        taEvent.setRows(5);
        jScrollPane2.setViewportView(taEvent);

        tblDataBaseData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblDataBaseData);

        dpDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpDateActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\logo.png")); // NOI18N

        spinerTime.setModel(new javax.swing.SpinnerDateModel());
        spinerTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spinerTime.setEditor(new javax.swing.JSpinner.DateEditor(spinerTime, "dd.mm.yyyy hh:mm:ss"));
        spinerTime.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                spinerTimeAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jCheckBox1.setText("Ustaw alarm");

        menuCalendar.setText("Pomoc");

        menuCalendar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuCalendar1.setText("O programie");
        menuCalendar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCalendar1ActionPerformed(evt);
            }
        });
        menuCalendar.add(menuCalendar1);

        jMenuBar1.add(menuCalendar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnCancelEvent)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAddEvent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDeleteEvent)
                        .addGap(242, 242, 242)
                        .addComponent(btnExit))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spinerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddEvent)
                            .addComponent(btnCancelEvent)
                            .addComponent(btnExit)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteEvent)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btnDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEventActionPerformed
       Update_table1();
    }//GEN-LAST:event_btnDeleteEventActionPerformed

    private void dpDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dpDateActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEventActionPerformed
        System.out.println(dpDate.getDate());
        System.out.println(spinerTime.getNextValue());
        
    }//GEN-LAST:event_btnAddEventActionPerformed

    private void menuCalendar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCalendar1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Hello Calendar !");
    }//GEN-LAST:event_menuCalendar1ActionPerformed

    private void spinerTimeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_spinerTimeAncestorAdded
        
    }//GEN-LAST:event_spinerTimeAncestorAdded

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        DBConnect.getConnection();
        Processing.dbConnection();
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Application().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddEvent;
    private javax.swing.JButton btnCancelEvent;
    private javax.swing.JButton btnDeleteEvent;
    private javax.swing.JButton btnExit;
    private org.jdesktop.swingx.JXDatePicker dpDate;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenu menuCalendar;
    private javax.swing.JMenuItem menuCalendar1;
    private javax.swing.JSpinner spinerTime;
    private javax.swing.JTextArea taEvent;
    private javax.swing.JTable tblDataBaseData;
    // End of variables declaration//GEN-END:variables
}
