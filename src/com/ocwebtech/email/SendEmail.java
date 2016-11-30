package com.ocwebtech.email;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private String user = "nialloc9@gmail.com";
    private String pass = "";
    
    public SendEmail(String to, String subject, String msg) {
        
        Properties prop = new Properties();
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", true);
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.post", "587");
        
        //Session is a class for creating mail
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){ //overrided method
                return new javax.mail.PasswordAuthentication(user, pass); //authenticates pass and username
            }
        });
        
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("no-reply@gmail.com")); //who the message is from
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); //who it is to
            message.setSubject(subject); //set setubject
            message.setText(msg); //set message to send
            
            //now let's send the message
            Transport.send(message);
            
            System.out.println("Mail Sent");
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
