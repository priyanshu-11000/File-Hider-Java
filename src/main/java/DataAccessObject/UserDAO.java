package DataAccessObject;

import DataBaseConnection.MyConnection;
import Model.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static boolean isUserExits(String email) throws SQLException {
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String e = rs.getString(1);
            if(e.equals(email)){
                System.out.println("User found SuccessFully:) ");
                return true;
            }
        }
        return false;
    }

    public static int savaUser(Users user) throws SQLException{
        Connection connection = MyConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement("insert into users values(default , ? , ? )");
        ps.setString(1,user.getName());
        ps.setString(2,user.getEmail());
        return ps.executeUpdate();
    }
}
