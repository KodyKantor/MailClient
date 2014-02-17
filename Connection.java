package mailClient;

import java.util.Properties;

import javax.mail.Session;

public abstract class Connection
{
	protected String username;
	protected String password;
	protected String host;
	protected Session session;
	
	private DatabaseAccessor dba = DatabaseAccessor.getInstance();
	
	/**
	 * 
	 * a session is created using a default "mail.smtps.auth" = "true"
	 * property.

	 * 
	 * TODO Find a way to encrypt the database
	 * 		Get the user credentials from the database. If they're not there,
	 * 		then we need to query the user (maybe this should be done on the AccountCreator side)
	 */
	public Connection()
	{		
		AccountAdmin admin = null;
		try
		{
			admin = new AccountAdmin();
		}
		catch(MailException e)
		{
			throw new MailException("The user isn't logged in");
		}
		Properties props = new Properties();
		props.put("mail.smtps.auth", "true");
		props.put("mail.store.protocol", "imaps");
		
		this.username = admin.getUsername();
		this.password = admin.getPassword();
		this.host = admin.getHost();
		
		this.session = Session.getInstance(props, new GMailAuth(this.username, this.password));
		
		
	}
	
	/**
	 * Adds the provided messages to the database.
	 * 
	 * TODO what should this method take?
	 * 	An array in the form:
	 * 		[to][name]
	 * 		[from][name]
	 * 		[content][xyzabc]
	 * 	or a K,V pair thing?
	 */
	protected void addMessagesToDB()
	{
		
	}
}
