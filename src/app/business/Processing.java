
package app.business;

import app.dataHelp.DBConnect;
import java.sql.*;

/**
 *
 * @author Emil
 */
public class Processing {
    
    public static void dbConnection(){
         Connection connection = null;



        try{
            connection = DBConnect.getConnection();
            if(connection !=null);
            System.out.println("Connection established!");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null)
                try{
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

        }
    }
}
