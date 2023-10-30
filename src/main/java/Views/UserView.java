package Views;

import DataAccessObject.DataDAO;
import Model.Data;

import javax.annotation.processing.FilerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private String email;
    UserView(String email){
        this.email = email;
    }
    public void home(){
        do {
            System.out.println("Welcome! "+ this.email);
            System.out.println("Press 1 to Show Hidden files");
            System.out.println("Press 2 to Hide a new the file");
            System.out.println("Press 3 to unHide a file");
            System.out.println("Press 0 to EXIT.");
            System.out.print("Press No. now : ");
            Scanner sc = new Scanner(System.in);
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch){
                case 1-> {
                    try {
                        List<Data> files = DataDAO.getAllFiles(this.email);
                        System.out.println("**ID**     ||     **File Name**");
                        for (Data file : files) {
                            System.out.println(file.getId()+ " - " + file.getFileName());
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                case 2 ->{
                    System.out.print("Enter the file Path : ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    Data file = new Data(0,f.getName(),path,this.email);
                    try {
                        DataDAO.hideFiles(file);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                case 3 -> {
                    List<Data> files = null;
                    try {
                        files = DataDAO.getAllFiles(this.email);
                        System.out.println("**ID**     ||      **File Name**");
                        for (Data file : files) {
                            System.out.println(file.getId() + " - " + file.getFileName());
                        }
                        System.out.println("Enter the ID of the file to unHide that file : ");
                        int id = Integer.parseInt(sc.nextLine());
                        boolean isValidID = false;
                        for (Data file : files) {
                            if (file.getId() == id) {
                                isValidID = true;
                                break;
                            }
                        }
                        if (isValidID) {
                            DataDAO.unHide(id);
                        } else {
                            System.out.println("Wrong ID entered!");
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }
                case 0 ->{
                    System.exit(0);
                }
            }

        }while(true);
    }
}
