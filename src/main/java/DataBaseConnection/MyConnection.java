package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
    private static boolean hasCodeExecuted = false;
    public static Connection connection;
    // This method is created for connecting the connection between database and java app.
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FileHiderProject?useTLS=false","root","7247bunty");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // Static variable to track whether the code has been executed
        if(!hasCodeExecuted){
            System.out.println("Connection is Established between DataBase and your project:>> ");
            hasCodeExecuted=true;
        }
        return connection;
    }
   // This method is created to closing the connection between database and java app.
    public static void closeConnection(){
        if(connection != null){
            try {
                connection.close();
                System.out.println("Connection closed successfully!!");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
    }
}
