package Services;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendOTPService {
    public static void sendOTP(String email, String genOTP) {
        // Recipient's email ID needs to be mentioned.
        String to = email;

        // Sender's email ID needs to be mentioned
        String from = "priyanshu00968@gmail.com";

        // Assuming you are sending email from through gmails smtp
//        String host = "smtp.gmail.com";

        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", "587");
//        properties.put("mail.smtp.tls.enable", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.auth", "true");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS


        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "hrxdjnqdfoytnsox");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("File Enc ka OTP");

            // Now set the actual message
            message.setText("Your One time Password for File Enc app is " + genOTP);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}
