package weatherGenie;
/**
 * 
 */


import javax.mail.Message.RecipientType;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.TransportStrategy;

/**
 *
 * @author doraemon
 */
public class SendEmail {
	public static boolean sendEmail(String toEmailName, String toEmailAddress, String subject, String emailText){
		final String emailUserId = "JavaCornerstone17Test";
		final String password = "doAGoodJob!!";
		final String fromEmailId = emailUserId + "@gmail.com";
		
		Email email = new EmailBuilder()
			    .from("Weather Program", fromEmailId)
			    .to(toEmailName, toEmailAddress)
			    .subject(subject)
			    .text(emailText)
			    .build();

			//new Mailer().sendMail(email);
		
		try{
			new Mailer("smtp.gmail.com", 465, emailUserId, password, TransportStrategy.SMTP_SSL).sendMail(email);
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
			

			
	return true;
	}
	
	public static boolean sendEmailHTML(String toEmailName, String toEmailAddress, String subject, String emailHTML){
		final String emailUserId = "JavaCornerstone17Test";
		final String password = "doAGoodJob!!";
		final String fromEmailId = emailUserId + "@gmail.com";
		
		Email email = new Email();
		email.setFromAddress(toEmailName, fromEmailId);
		email.addRecipient(toEmailName, toEmailAddress, RecipientType.TO);
		email.setTextHTML(emailHTML);
		email.setSubject(subject);
		//new Mailer().sendMail(email);
		
		try{
			new Mailer("smtp.gmail.com", 465, emailUserId, password, TransportStrategy.SMTP_SSL).sendMail(email);
			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
			

			
	return true;
	}
}
