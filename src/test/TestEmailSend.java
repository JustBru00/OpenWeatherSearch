/**
 * 
 */
package test;

import weatherGenie.SendEmail;

/**
 * @author read
 *
 */
public class TestEmailSend {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TEst sending email
		SendEmail.sendEmail("Justin Brubaker", "justbru00@gmail.com", "Test Email", "This is a test email");

	}

}
