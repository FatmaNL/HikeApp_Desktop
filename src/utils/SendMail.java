package utils;


import java.util.Properties;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

public class SendMail 
{
    

    public static void send(String recepient,int mot) throws MessagingException 
    {
        System.out.println("Preparing Send email");
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        String myAccountEmail ="hikeapppidev@gmail.com";
        String password ="hikeapp123";
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
            Message message = prepareMessage(session,myAccountEmail,recepient,mot);
            Transport.send(message);
            System.out.println("message sent");
    }

    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,int mot) throws AddressException, MessagingException {
       
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Mot de passe");
            message.setText(mot+" Est Votre code de verification du compte HikeApp");
            return message;
    }}
