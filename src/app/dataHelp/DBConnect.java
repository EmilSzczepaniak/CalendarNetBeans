package app.dataHelp;

import java.sql.*;

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
