package app;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Emil
 */
public class DBConnect {
    static Connection con = null;
    static Statement st = null;
    static ResultSet rs = null;
    static String s;

    public static Connection getConnection(){
     
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            ////////////////////////////////////////
            //st = connection.createStatement();
            //s = "select * from events";
            //rs = st.executeQuery(s);
           // ResultSetMetaData rmst = rs.getMetaData();
            //int c = rmst.getColumnCount();
            //Vector column = new Vector(c);
            //for(int i = 1; i <= c; i++){
            //    column.add(rmst.getColumnName(i));
           // }
            //Vector data = new Vector();
            //Vector row = new Vector();
            //while(rs.next()){
               // row = new Vector(c);
              //  for(int i = 1; i<=c; i++){
              //      row.add(rs.getString(i));
              //  }
              //  data.add(row);
            //}
            //JFrame frame = new JFrame();
            //frame.setSize(500,120);
           // frame.setLocationRelativeTo(null);
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //JPanel panel = new JPanel();
            //JTable table = new JTable(data,column);
            //JScrollPane jsp = new JScrollPane(table);
            //panel.setLayout(new BorderLayout());
            //panel.add(jsp,BorderLayout.CENTER);
           // frame.setContentPane(panel);
            //frame.setVisible(true);
            
            ////////
            
            
            ////////////////////////////////////////////
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Calendar","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
