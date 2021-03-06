/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import app.dataHelp.DBConnect;
import app.business.Processing;
import app.business.ApplicationInterface;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Emil
 */
public class Application extends javax.swing.JFrame implements ApplicationInterface{
    static String dateAlarm ;
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    JLabel myImage;
    String id;
    /**
     * Creates new form Application
     */
    public Application() {
        super("Calendar by Emil Szczepaniak");
        
        initComponents();
        conn = DBConnect.getConnection();
        Update_table();
        showCurrentTime();
        
        setIconImage(new ImageIcon("app_icon.png").getImage());
        
    }
    
    
    @SuppressWarnings("empty-statement")
    public void ValidationOfEvents(){
        
    }
    @Override
    public void saveToCSV() throws IOException{
         String separator = ";";
        try{
            FileWriter csv = new FileWriter("calendar.csv");
           for(int i =0;i < tblDataBaseData.getColumnCount(); i++){
               csv.write(tblDataBaseData.getColumnName(i) + "\t");
               csv.write(separator);
              
           } 
           csv.write("\n");
           
           for(int i=0; i<tblDataBaseData.getRowCount(); i++){
               for(int j=0; j<tblDataBaseData.getColumnCount();j++){
                   csv.write(tblDataBaseData.getValueAt(i, j).toString()+"\t");
                   csv.write(separator);
               }
               csv.write("\n");
           }
           csv.close();
           
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    }
    public void deleteEvent(){
        try{
           
           String sql = "delete from events where id =?";
           pst = conn.prepareStatement(sql);
           pst.setString(1,id);
           pst.execute();
           JOptionPane.showMessageDialog(null,"Usunięto");
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       Update_table();
           
    }
    public void showDataFromDataBaseTable(){
         try{
           
           int row = tblDataBaseData.getSelectedRow();
           String tblDataBase_click=(tblDataBaseData.getModel().getValueAt(row, 0)).toString();
           String sql = "select* from events where id="+tblDataBase_click+"";
           pst=conn.prepareStatement(sql);
           rs=pst.executeQuery();
           
           if(rs.next()){
               String add1 = rs.getString("id");
               //tfID.setText(add1);
               id = add1;
               String add2 = rs.getString("miejsce");
               tfPlace.setText(add2);
               Date add3 = rs.getDate("data");
               dcEventDate.setDate(add3);
               taEvent.setText("");
               String add4 = rs.getString("opis");
               taEvent.append(add4);
           }
           
           
       } catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void setAlarm(){
        String alarmTime =("1970.01.01 00:00:00");
        try{
        alarmTime = (new SimpleDateFormat("yyyy.MM.dd kk:mm:ss").format(spinerTime.getValue()));
        
        }catch(Exception e ){
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "Alarm ustawiony na : "+alarmTime);
    }
    public void addToDatabase(){
        try {

            String sql = "insert into events values(id,?,?,?)";
            //String sql1 = "select data from events where data = 2016-05-19";
            
            //pst = conn.prepareStatement(sql1);
            
           // pst.execute();
            
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
    }
    


    public void Update_table() {
        try {
            String sql = "SELECT* FROM events ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            tblDataBaseData.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

   public void PlayAlarm(){
   
    File audioFile = new File("alarm_alert.WAV");
    try{
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(audioFile));
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        int reply;
       do{ 
            reply = JOptionPane.showConfirmDialog(null,
                                          "Czy chcesz wyłączyć alarm ?",
                                          "Alarm",
                                          JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
       }while(reply != JOptionPane.OK_OPTION);clip.stop();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    
   }
   
   public void showCurrentTime(){
      
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

        jScrollPane2 = new javax.swing.JScrollPane();
        taEvent = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDataBaseData = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        timeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        btnSetAlarm = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        btnDeleteEvent = new javax.swing.JButton();
        btnAddEvent = new javax.swing.JButton();
        tfPlace = new javax.swing.JTextField();
        dcEventDate = new com.toedter.calendar.JDateChooser();
        spinerTime = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        mbCalendar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuDelete = new javax.swing.JMenu();
        menuDeleteOlderThan1Day = new javax.swing.JMenuItem();
        menuDeleteOlderThanWeek = new javax.swing.JMenuItem();
        menuDeleteOlderThanMonth = new javax.swing.JMenuItem();
        menuDeleteOlderThanYear = new javax.swing.JMenuItem();
        menuDeleteOlderThanAnyValue = new javax.swing.JMenuItem();
        menuCalendar = new javax.swing.JMenu();
        menuCalendar1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(860, 560));
        getContentPane().setLayout(null);

        taEvent.setColumns(20);
        taEvent.setRows(5);
        jScrollPane2.setViewportView(taEvent);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(20, 323, 330, 120);

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
        jScrollPane3.setBounds(382, 183, 440, 260);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setForeground(new java.awt.Color(0, 102, 102));
        jPanel1.setToolTipText("");

        jPanel2.setBackground(new java.awt.Color(22, 34, 47));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel2.setForeground(new java.awt.Color(0, 102, 102));

        timeLabel.setBackground(new java.awt.Color(255, 255, 255));
        timeLabel.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeLabel.setText("TIME");

        dateLabel.setBackground(new java.awt.Color(255, 255, 255));
        dateLabel.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateLabel.setText("DATE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSetAlarm.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\alarm_icon.png")); // NOI18N
        btnSetAlarm.setText("Ustaw alarm");
        btnSetAlarm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSetAlarm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetAlarmActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\Calendar_Logo.png")); // NOI18N

        btnExit.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\exit_icon.png")); // NOI18N
        btnExit.setText("Wyjdź");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel4.setText("Data");

        jLabel3.setText("Miejsce");

        btnDeleteEvent.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\delete_icon.png")); // NOI18N
        btnDeleteEvent.setText("Usuń");
        btnDeleteEvent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeleteEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEventActionPerformed(evt);
            }
        });

        btnAddEvent.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\add_icon.png")); // NOI18N
        btnAddEvent.setText("Dodaj");
        btnAddEvent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEventActionPerformed(evt);
            }
        });

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

        jLabel5.setText("Godzina");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(166, 166, 166)
                        .addComponent(btnSetAlarm)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addComponent(btnDeleteEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(185, 185, 185)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinerTime, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcEventDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSetAlarm)
                    .addComponent(spinerTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 850, 500);

        mbCalendar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));

        jMenu1.setText("Plik");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\menu_save_icon.png")); // NOI18N
        jMenuItem1.setText("Zapisz");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        mbCalendar.add(jMenu1);

        menuDelete.setText("Usuń");

        menuDeleteOlderThan1Day.setText("Starsze niż 1 dzień");
        menuDeleteOlderThan1Day.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteOlderThan1DayActionPerformed(evt);
            }
        });
        menuDelete.add(menuDeleteOlderThan1Day);

        menuDeleteOlderThanWeek.setText("Starsze niż tydzień");
        menuDeleteOlderThanWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteOlderThanWeekActionPerformed(evt);
            }
        });
        menuDelete.add(menuDeleteOlderThanWeek);

        menuDeleteOlderThanMonth.setText("Starsze niż miesiąc");
        menuDeleteOlderThanMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteOlderThanMonthActionPerformed(evt);
            }
        });
        menuDelete.add(menuDeleteOlderThanMonth);

        menuDeleteOlderThanYear.setText("Starsze niż rok");
        menuDeleteOlderThanYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteOlderThanYearActionPerformed(evt);
            }
        });
        menuDelete.add(menuDeleteOlderThanYear);

        menuDeleteOlderThanAnyValue.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\terminal_icon.png")); // NOI18N
        menuDeleteOlderThanAnyValue.setText("Wprowadź wartość");
        menuDeleteOlderThanAnyValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDeleteOlderThanAnyValueActionPerformed(evt);
            }
        });
        menuDelete.add(menuDeleteOlderThanAnyValue);

        mbCalendar.add(menuDelete);

        menuCalendar.setText("Pomoc");

        menuCalendar1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        menuCalendar1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Emil\\Documents\\GitHub\\CalendarNetBeans\\icon\\about_menu_icon.png")); // NOI18N
        menuCalendar1.setText("O programie");
        menuCalendar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCalendar1ActionPerformed(evt);
            }
        });
        menuCalendar.add(menuCalendar1);

        mbCalendar.add(menuCalendar);

        setJMenuBar(mbCalendar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnDeleteEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEventActionPerformed
       deleteEvent();
    }//GEN-LAST:event_btnDeleteEventActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        dispose();
        System.exit(0);
        
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnAddEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEventActionPerformed

        addToDatabase();
    }//GEN-LAST:event_btnAddEventActionPerformed

    private void menuCalendar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCalendar1ActionPerformed
        JOptionPane.showMessageDialog(rootPane, " \n\n\n Aplikacja Calendar pozwala zapisywanie terminów zdarzeń, ich opisów i miejsc \n Ustawianie przypomnień dźwiękowych nawybrany czas \n Korzystanie z graficznego kalendarza \n Kasowanie zdarzeń starszych niż podana ilość dni \n Wyświetlanie okna dialogowoego o programie \n Posiada menu główne dające dostęp do kilku z funkcji aplikacji \n Aplikacja Calendar obsługiwana jest przy pomocy myszy i klawatury \n \n \n Autorzy :\n Emil Szczepaniak ");
    }//GEN-LAST:event_menuCalendar1ActionPerformed

    private void spinerTimeAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_spinerTimeAncestorAdded

    }//GEN-LAST:event_spinerTimeAncestorAdded

    private void tblDataBaseDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataBaseDataMouseClicked
      
       showDataFromDataBaseTable();
    }//GEN-LAST:event_tblDataBaseDataMouseClicked

    private void btnSetAlarmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetAlarmActionPerformed
        setAlarm();
    }//GEN-LAST:event_btnSetAlarmActionPerformed

    private void menuDeleteOlderThan1DayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteOlderThan1DayActionPerformed
        
        try{
           String sql = "DELETE FROM events WHERE data < DATE_SUB(NOW(), INTERVAL 1 DAY);";
           pst = conn.prepareStatement(sql);
           int deleted = pst.executeUpdate();
           
           
           if(deleted == 0)
           JOptionPane.showMessageDialog(null,"Nie naleziono zdarzenia starszego niż 1 dzień");
           else{
               JOptionPane.showMessageDialog(null,"Usunięto");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       Update_table();
    }//GEN-LAST:event_menuDeleteOlderThan1DayActionPerformed

    private void menuDeleteOlderThanWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteOlderThanWeekActionPerformed
         try{
           String sql = "DELETE FROM events WHERE data < DATE_SUB(NOW(), INTERVAL 1 WEEK);";
           pst = conn.prepareStatement(sql);
           int deleted = pst.executeUpdate();
           if(deleted == 0)
           JOptionPane.showMessageDialog(null,"Nie naleziono zdarzenia starszego niż tydzień");
           else{
               JOptionPane.showMessageDialog(null,"Usunięto");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       Update_table();
    }//GEN-LAST:event_menuDeleteOlderThanWeekActionPerformed

    private void menuDeleteOlderThanAnyValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteOlderThanAnyValueActionPerformed
         try{
            String value = JOptionPane.showInputDialog("Usuń starsze niż :");
                    
           String sql = "DELETE FROM events WHERE data < DATE_SUB(NOW(), INTERVAL ? DAY);";
           pst = conn.prepareStatement(sql);
           pst.setString(1,value);
           int deleted = pst.executeUpdate();
           
           if(deleted == 0)
               
           JOptionPane.showMessageDialog(null,"Nie naleziono zdarzenia starszego niż "+ value + " dni");
           else{
               JOptionPane.showMessageDialog(null,"Usunięto");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
         
       Update_table();
    }//GEN-LAST:event_menuDeleteOlderThanAnyValueActionPerformed

    private void menuDeleteOlderThanMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteOlderThanMonthActionPerformed
         try{
           String sql = "DELETE FROM events WHERE data < DATE_SUB(NOW(), INTERVAL 1 MONTH);";
           pst = conn.prepareStatement(sql);
           int deleted =pst.executeUpdate();
           if(deleted == 0)
           JOptionPane.showMessageDialog(null,"Nie naleziono zdarzenia starszego niż miesiąc");
           else{
               JOptionPane.showMessageDialog(null,"Usunięto");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       Update_table();
    }//GEN-LAST:event_menuDeleteOlderThanMonthActionPerformed

    private void menuDeleteOlderThanYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDeleteOlderThanYearActionPerformed
         try{
           String sql = "DELETE FROM events WHERE data < DATE_SUB(NOW(), INTERVAL 1 YEAR);";
           pst = conn.prepareStatement(sql);
           int deleted = pst.executeUpdate();
           if(deleted == 0)
           JOptionPane.showMessageDialog(null,"Nie naleziono zdarzenia starszego niż rok");
           else{
               JOptionPane.showMessageDialog(null,"Usunięto");
           }
       }catch(Exception e){
           JOptionPane.showMessageDialog(null,e);
       }
       Update_table();
    }//GEN-LAST:event_menuDeleteOlderThanYearActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       
        JFileChooser fs = new JFileChooser(".");
        
        
        fs.setDialogTitle("Zapisz");
        //fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //FileFilter filter = (FileFilter) new ExtensionFileFilter("JPG and JPEG", new String[] { "JPG", "JPEG" });
        //fs.setFileFilter((javax.swing.filechooser.FileFilter) filter);
         fs.setCurrentDirectory(new java.io.File("C:/Users/Emil/Desktop"));
        int result = fs.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            try {
                File fi = fs.getSelectedFile();
            String separator = ";";
       
            FileWriter csv = new FileWriter(fi);
           for(int i =0;i < tblDataBaseData.getColumnCount(); i++){
               csv.write(tblDataBaseData.getColumnName(i) + "\t");
               csv.write(separator);
              
           } 
           csv.write("\n");
           
           for(int i=0; i<tblDataBaseData.getRowCount(); i++){
               for(int j=0; j<tblDataBaseData.getColumnCount();j++){
                   csv.write(tblDataBaseData.getValueAt(i, j).toString()+"\t");
                   csv.write(separator);
               }
               csv.write("\n");
           }
           csv.close();
           
        
            JOptionPane.showMessageDialog(null, "Zapisano do CSV !");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
            
            
            
        }
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
    private javax.swing.JButton btnDeleteEvent;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSetAlarm;
    private javax.swing.JLabel dateLabel;
    private com.toedter.calendar.JDateChooser dcEventDate;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenuBar mbCalendar;
    private javax.swing.JMenu menuCalendar;
    private javax.swing.JMenuItem menuCalendar1;
    private javax.swing.JMenu menuDelete;
    private javax.swing.JMenuItem menuDeleteOlderThan1Day;
    private javax.swing.JMenuItem menuDeleteOlderThanAnyValue;
    private javax.swing.JMenuItem menuDeleteOlderThanMonth;
    private javax.swing.JMenuItem menuDeleteOlderThanWeek;
    private javax.swing.JMenuItem menuDeleteOlderThanYear;
    private javax.swing.JSpinner spinerTime;
    private javax.swing.JTextArea taEvent;
    private javax.swing.JTable tblDataBaseData;
    private javax.swing.JTextField tfPlace;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables

}
