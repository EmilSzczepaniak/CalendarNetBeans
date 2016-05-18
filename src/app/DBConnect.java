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
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Calendar","root","");
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
