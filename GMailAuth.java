package mailClient;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GMailAuth extends Authenticator
{
	String user;
	String pass;
	public GMailAuth(String user, String pass)
	{
		super();
		this.user = user;
		this.pass = pass;
	}
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(user, pass);
	}

}
