/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.applet.AudioClip;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.lang.Thread.sleep;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
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
        super("Calendar by Emil Szczepaniak");
        
        initComponents();
        conn = DBConnect.getConnection();
        Update_table();
        showCurrentTime();
        
       

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

   private void PlayAlarm(){
   
    File audioFile = new File("alarm.WAV");
    
    
    try{
        
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(audioFile));
        clip.start();
        
        int reply = JOptionPane.showConfirmDialog(null,
                                          "Czy chcesz wyłączyć alarm ?",
                                          "Alarm",
                                          JOptionPane.YES_NO_OPTION);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
       if(reply == JOptionPane.OK_OPTION){
           clip.stop();  
       }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    
   }
   
   private void showCurrentTime(){
      
    Thread clock = new Thread(){
           public void run(){
              for(;;){
           Calendar cal = new GregorianCalendar();
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
           int day = cal.get(Calendar.DAY_OF_MONTH);
           int month = cal.get(Calendar.MONTH)+1;
           int year = cal.get(Calendar.YEAR);
           
           java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("kk:mm:ss");
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
           String alarm = dateAlarm;
           dateAlarm = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(spinerTime.getValue());
                try{
                    
                    System.out.println(currentDateAndTime);
                    System.out.println(alarm);
                    if(currentDateAndTime.equals(alarm)){
                       // Toolkit.getDefaultToolkit().beep();
                       // JOptionPane.showMessageDialog(null, date);
                        PlayAlarm();
                        
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
        tfID = new javax.swing.JTextField();
        tfPlace = new javax.swing.JTextField();
        dcEventDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        btnSetAlarm = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuCalendar = new javax.swing.JMenu();
        menuCalendar1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        btnAddEvent.setText("Dodaj");
        btnAddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEventActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddEvent);
        btnAddEvent.setBounds(20, 455, 62, 32);

        btnCancelEvent.setText("Anuluj");
        btnCancelEvent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelEventMouseClicked(evt);
            }
        });
        btnCancelEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelEventActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelEvent);
        btnCancelEvent.setBounds(282, 455, 65, 32);

        btnDeleteEvent.setText("Usuń");
        btnDeleteEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEventActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeleteEvent);
        btnDeleteEvent.setBounds(382, 455, 59, 32);

        btnExit.setText("Wyjdź");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(683, 455, 64, 32);

        taEvent.setColumns(20);
        taEvent.setRows(5);
        jScrollPane2.setViewportView(taEvent);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 371, 327, 72);

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

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(382, 199, 365, 244);

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\logo.png")); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(296, 34, 268, 90);

        spinerTime.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.HOUR_OF_DAY));
        spinerTime.setToolTipText("");
        spinerTime.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        spinerTime.setEditor(new javax.swing.JSpinner.DateEditor(spinerTime, "yyyy-MM-dd kk:mm:ss"));
        spinerTime.setName(""); // NOI18N
        spinerTime.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                spinerTimeAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(spinerTime);
        spinerTime.setBounds(71, 315, 137, 22);

        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });
        getContentPane().add(tfID);
        tfID.setBounds(71, 199, 147, 24);
        getContentPane().add(tfPlace);
        tfPlace.setBounds(71, 235, 147, 24);
        getContentPane().add(dcEventDate);
        dcEventDate.setBounds(71, 271, 194, 29);

        jLabel2.setText("ID");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 203, 11, 16);

        jLabel3.setText("Miejsce");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 239, 44, 16);

        jLabel4.setText("Data");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 281, 26, 16);

        jLabel5.setText("Godzina");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 320, 45, 16);

        timeLabel.setBackground(new java.awt.Color(0, 0, 0));
        timeLabel.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(0, 0, 51));
        timeLabel.setText("TIME");
        getContentPane().add(timeLabel);
        timeLabel.setBounds(89, 34, 166, 42);

        dateLabel.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(0, 0, 51));
        dateLabel.setText("DATE");
        getContentPane().add(dateLabel);
        dateLabel.setBounds(71, 82, 166, 42);

        btnSetAlarm.setText("Ustaw alarm");
        btnSetAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetAlarmActionPerformed(evt);
            }
        });
        getContentPane().add(btnSetAlarm);
        btnSetAlarm.setBounds(228, 312, 102, 32);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(0, 102, 102));
        jPanel1.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), java.awt.Color.lightGray));
        jPanel2.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 208, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(543, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(352, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 500);

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
        System.exit(0);
        
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

    private void btnCancelEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelEventActionPerformed

    }//GEN-LAST:event_btnCancelEventActionPerformed

    private void btnCancelEventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelEventMouseClicked
        
    }//GEN-LAST:event_btnCancelEventMouseClicked

    private void btnSetAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetAlarmActionPerformed
        String alarmTime =("1970.01.01 00:00:00");
        try{
        alarmTime = (new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(spinerTime.getValue()));
        
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "Alarm ustawiony na : "+alarmTime);
    }//GEN-LAST:event_btnSetAlarmActionPerformed

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
                if ("Windows".equals(info.getName())) {
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
    private javax.swing.JButton btnSetAlarm;
    private javax.swing.JLabel dateLabel;
    private com.toedter.calendar.JDateChooser dcEventDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
