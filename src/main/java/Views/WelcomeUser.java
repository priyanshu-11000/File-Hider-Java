package Views;

import DataAccessObject.UserDAO;
import Model.Users;
import Services.GenerateOTP;
import Services.SendOTPService;
import Services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class WelcomeUser {
    public void welcomeScreen(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome User to this App:)");
        System.out.print("Press 1 to LOGIN : \nPress 2 to SIGNUP : \nPress 0 EXIT : \n");
        System.out.print("Enter Button : ");

        int choice = 0;
        try {
            choice = Integer.parseInt(br.readLine());
        }catch(IOException ex){
            ex.printStackTrace();
        }

        switch (choice){
            case 1 -> login();
            case 2 -> signUp();
            case 0 -> System.exit(0);
        }
    }

    private void signUp() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Your Name : ");
        String name = sc.nextLine();
        System.out.print("Enter Your Email : ");
        String email = sc.nextLine();

        String genOTP = GenerateOTP.getOTP();
        SendOTPService.sendOTP(email,genOTP);
        System.out.print("Enter the valid OTP : ");
        String otp = sc.nextLine();
        if(otp.equals(genOTP)){
            Users user = new Users(name , email);
            int response = UserService.saveUser(user);
            switch (response){
                case 0 -> System.out.println("User registered.");
                case 1 -> System.out.println("User added in the Database.");
            }
        }
    }

    private void login() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Registered Email : ");
        String email = sc.nextLine();
        try{
            if(UserDAO.isUserExits(email)){
                String genOTP = GenerateOTP.getOTP();
                SendOTPService.sendOTP(email,genOTP);
                System.out.println("Enter the OTP sent to your mail for Verification : ");
                String otp = sc.nextLine();
                if(otp.equals(genOTP)){
//                    System.out.println("Welcome^-^");
                    new UserView(email).home();
                }
                else {
                    System.out.println("Wrong OTP Entered!");
                }
            }
            else System.out.println("User Not Found!");
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
