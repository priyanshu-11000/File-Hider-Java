package Services;

import DataAccessObject.UserDAO;
import Model.Users;

import java.sql.SQLException;

public class UserService {
    public static Integer saveUser(Users user){
        try {
            if(UserDAO.isUserExits(user.getEmail())){
                return 0;
            }
            else return UserDAO.savaUser(user);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
