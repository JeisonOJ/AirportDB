package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    private static final String url = "jdbc:mysql://unk41ftveiet4ywo:SDQsQIjI5GREQp19dTiF@bftqv7jskif3gjb8cy9a-mysql.services.clever-cloud.com:3306/bftqv7jskif3gjb8cy9a";
    private static final String user = "unk41ftveiet4ywo";
    private static final String password = "SDQsQIjI5GREQp19dTiF";
    public static Connection connection = null;
    public static Connection openConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to database");
        }catch (SQLException error){
            System.out.println("Error in database\n"+error.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Error in driver\n"+ e.getMessage());
        }
        return connection;
    }

    public static void closeConnection(){
        if (connection != null){
            try{
                connection.close();
                System.out.println("Closed connection");
            }catch (SQLException error){
                System.out.println("Error closing the database\n"+error.getMessage());
            }
        }
    }

}
