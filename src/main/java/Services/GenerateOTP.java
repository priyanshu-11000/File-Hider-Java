package Services;

import java.util.Random;

public class GenerateOTP {
    public static String getOTP(){
        Random random = new Random();
                String str = String.format("%06d",random.nextInt(1000000));
//        return String.format("%06d",random.nextInt(1000000));
        System.out.println("Your OTP is : " + str);
        return str;
    }
}
