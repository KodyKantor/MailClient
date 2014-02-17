package mailClient;

import javax.mail.*;

public class Receive extends Connection
{
	private Folder inbox;

	public Receive()
	{
		super();
		this.getFolders();
	}

	/**
	 * getFolders gets all of the folders from the mailbox. It adds new messages
	 * to the database.
	 * 
	 * TODO Find a way to get the names of all the folders present in the
	 * mailbox
	 */
	private void getFolders()
	{
		Store store = null;

		try
		{
			store = super.session.getStore();
			store.connect("imap.gmail.com", super.username, super.password);
		} catch (NoSuchProviderException e)
		{
			throw new MailException("Couldn't get the store from the session: "
					+ e.getMessage());
		} catch (MessagingException e)
		{
			throw new MailException("Error connecting: " + e.getMessage());
		}
		try
		{
			this.inbox = store.getFolder("Inbox");
		} catch (MessagingException e)
		{
			throw new MailException("Error getting the default folder: "
					+ e.getMessage());
		}
		
		printMessages();
	}

	public int getMessageCount()
	{
		int result = 0;
		try
		{
			this.inbox.open(Folder.READ_ONLY);
			result = this.inbox.getMessageCount();
			this.inbox.close(true);
		} catch (MessagingException e)
		{
			throw new MailException("Error getting message count: "
					+ e.getMessage());
		}
		return result;
	}

	private void printMessages()
	{
		System.out.println("You have " + this.getMessageCount() + " messages in your inbox");
		try
		{
			this.inbox.open(Folder.READ_ONLY);
//			Message[] messages = this.inbox.getMessages();
//			for (Message m : messages)
//			{
//				System.out.println("Received message from " + m.getFrom()[0]);
//			}
			this.inbox.close(true);
		} catch (MessagingException e)
		{
			throw new MailException("Error getting messages: " + e.getMessage());
		}
		

	}

}
