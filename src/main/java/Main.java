import DataBaseConnection.MyConnection;
import Views.WelcomeUser;

public class Main {
    public static void main(String[] args) {
  // first We need to connect to the database;
        MyConnection.getConnection();
        WelcomeUser w = new WelcomeUser();
        do {
            w.welcomeScreen();
        }while(true);
    }
}
