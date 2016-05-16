/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Emil
 */
public class Application extends javax.swing.JFrame {
    static String dateAlarm ;
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
        showCurrentTime();
        setAlarm();

    }


    private void Update_table() {
        try {
            String sql = "SELECT* FROM events ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            tblDataBaseData.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

   private void setAlarm(){
       Date currentDate = new Date();
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
       String dateString = dateFormat.format(currentDate);
       //System.out.println(dateString);
       //System.out.println(dateAlarm);
       if(chckbAlarm.isSelected()){
           dateAlarm = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(spinerTime.getValue());
       }
       if(dateString.equals(dateAlarm)){
           JOptionPane.showMessageDialog(null, "Alarm !");
       }
       
       
   
       
   }
   private void showCurrentTime(){
      
    Thread clock = new Thread(){
           public void run(){
              for(;;){
           Calendar cal = new GregorianCalendar();
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
           int day = cal.get(Calendar.DAY_OF_MONTH);
           int month = cal.get(Calendar.MONTH)+1;
           int year = cal.get(Calendar.YEAR);
           
           java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("hh:mm:ss");
           java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
           
      
           
          
           
           int second = cal.get(Calendar.SECOND);
           int minute= cal.get(Calendar.MINUTE);
           int hour = cal.get(Calendar.HOUR_OF_DAY);
           
           
           String time = (df.format(cal.getTime()));
           String date = (df1.format(cal.getTime()));
           String currentDateAndTime = (date+ " " +time );
           
           //System.out.println(hour+":"+minute+":"+second);
           timeLabel.setText(time);
           dateLabel.setText(date);
           String alarm = dateAlarm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(spinerTime.getValue());
                try{
                    
                    System.out.println(currentDateAndTime);
                    System.out.println(dateAlarm);
                    if(currentDateAndTime.equals(dateAlarm)){
                        JOptionPane.showMessageDialog(null, "Alarm !");
                    }
                   sleep(1000); 
                }catch(Exception e){
                    e.printStackTrace();
                    
                }
              } 
           }
           };
           clock.start();
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
        jLabel1 = new javax.swing.JLabel();
        spinerTime = new javax.swing.JSpinner();
        chckbAlarm = new javax.swing.JCheckBox();
        tfID = new javax.swing.JTextField();
        tfPlace = new javax.swing.JTextField();
        dcEventDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
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
        btnCancelEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelEventActionPerformed(evt);
            }
        });

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
        tblDataBaseData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataBaseDataMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDataBaseData);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\logo.png")); // NOI18N

        spinerTime.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinerTime.setToolTipText("");
        spinerTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spinerTime.setEditor(new javax.swing.JSpinner.DateEditor(spinerTime, "yyyy-MM-dd kk:mm:ss"));
        spinerTime.setName(""); // NOI18N
        spinerTime.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                spinerTimeAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        chckbAlarm.setText("Ustaw alarm");
        chckbAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chckbAlarmActionPerformed(evt);
            }
        });

        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });

        jLabel2.setText("ID");

        jLabel3.setText("Miejsce");

        jLabel4.setText("Data");

        jLabel5.setText("Godzina");

        timeLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLabel.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(0, 0, 51));
        timeLabel.setText("TIME");

        dateLabel.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(0, 0, 51));
        dateLabel.setText("DATE");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(206, 206, 206))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spinerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chckbAlarm))
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAddEvent)
                        .addGap(124, 124, 124)
                        .addComponent(btnCancelEvent))
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDeleteEvent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExit)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(timeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dateLabel)))
                .addGap(69, 69, 69)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dcEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(spinerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(chckbAlarm))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(74, 74, 74)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddEvent)
                    .addComponent(btnCancelEvent)
                    .addComponent(btnDeleteEvent)
                    .addComponent(btnExit))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEventActionPerformed
       try{
           String sql = "delete from events where id =?";
           pst = conn.prepareStatement(sql);
           pst.setString(1,tfID.getText());
           pst.execute();
           JOptionPane.showMessageDialog(null,"Usunięto");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       Update_table();
           
    }//GEN-LAST:event_btnDeleteEventActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEventActionPerformed

        try {

            String sql = "insert into events values(id,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, ((JTextField) dcEventDate.getDateEditor().getUiComponent()).getText());
            pst.setString(2, tfPlace.getText());
            pst.setString(3, taEvent.getText());

            pst.execute();
            JOptionPane.showMessageDialog(null, "Dodano nowe wydarznie!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        Update_table();

    }//GEN-LAST:event_btnAddEventActionPerformed

    private void menuCalendar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCalendar1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Hello Calendar !");
    }//GEN-LAST:event_menuCalendar1ActionPerformed

    private void spinerTimeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_spinerTimeAncestorAdded

    }//GEN-LAST:event_spinerTimeAncestorAdded

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDActionPerformed
        
    }//GEN-LAST:event_tfIDActionPerformed

    private void tblDataBaseDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataBaseDataMouseClicked
       try{
           int row = tblDataBaseData.getSelectedRow();
           String tblDataBase_click=(tblDataBaseData.getModel().getValueAt(row, 0)).toString();
           String sql = "select* from events where id="+tblDataBase_click+"";
           pst=conn.prepareStatement(sql);
           rs=pst.executeQuery();
           if(rs.next()){
               String add1 = rs.getString("id");
               tfID.setText(add1);
               String add2 = rs.getString("miejsce");
               tfPlace.setText(add2);
               Date add3 = rs.getDate("data");
               dcEventDate.setDate(add3);
               
           }
           
           
       } catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_tblDataBaseDataMouseClicked

    private void chckbAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chckbAlarmActionPerformed
       System.out.println(new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(spinerTime.getValue()));
        
    }//GEN-LAST:event_chckbAlarmActionPerformed

    private void btnCancelEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelEventActionPerformed
     
        System.out.println(new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(spinerTime.getValue()));
        
    }//GEN-LAST:event_btnCancelEventActionPerformed

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
    private javax.swing.JCheckBox chckbAlarm;
    private javax.swing.JLabel dateLabel;
    private com.toedter.calendar.JDateChooser dcEventDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenu menuCalendar;
    private javax.swing.JMenuItem menuCalendar1;
    private javax.swing.JSpinner spinerTime;
    private javax.swing.JTextArea taEvent;
    private javax.swing.JTable tblDataBaseData;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfPlace;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
