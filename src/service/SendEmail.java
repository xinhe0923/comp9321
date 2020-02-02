package service;

import dao.UserDao;
import dao.UserDaoImpl;
import dto.Cart;
import dto.User;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	 public static void sendEmail(String receiver, String subject, String text) throws MessagingException {
	        final Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.timeout", 20000);
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.user", "comp9321groupassignment@gmail.com");
	        props.put("mail.smtp.password", "admin9321");
	        props.put("mail.smtp.starttls.enable", "true");
	        try{
	        	Authenticator authenticator = new Authenticator() {
	        		@Override
	        		protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication( "comp9321groupassignment@gmail.com","admin9321");
	        		}
	        	};
	        	Session mailSession = Session.getDefaultInstance(props, authenticator);
	        	mailSession.setDebug(true);
	        	MimeMessage message = new MimeMessage(mailSession);
	        	InternetAddress form = new InternetAddress(props.getProperty("mail.smtp.user"));
	        	message.setFrom(form);
	        	message.setRecipient(RecipientType.TO, new InternetAddress(receiver));
	        	message.setSubject(subject);
	        	message.setText(text);
	        	Transport.send(message);
	    	} catch (AddressException e) {
				e.printStackTrace();
			} 
    }
    public static void sendRegistMail(User user) throws MessagingException {
        String address = user.getEmail();
        String subject = "Verify account";
        String text = 
                "verifying your email below\n" +
                "http://localhost:8080/comp9321ass2/control?uuid="+ user.getUuid() +"&action=verify";
        sendEmail(address,subject,text);
    }
    public static void sendSellMail(Cart cart) throws MessagingException {
        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserById(cart.getItem().getSeller_id());
        String address = user.getEmail();
        String subject = "Book Sold";
        String text = 
        	cart.getCount()+" "+cart.getItem().getTitle()+" is sold.";
        sendEmail(address,subject,text);
    }
}
