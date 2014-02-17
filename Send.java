package mailClient;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Send extends Connection
{
	private InternetAddress destination;
	private String text;
	private String subject;
	private AskUser asker = new AskUser();

	public Send()
	{
		super();
	}

	/**
	 * Sends the message. Uses superclass username, pass, host, etc.
	 */
	public void sendMessage()
	{
		if (text == null)
		{
			text = asker.askOpenEnded("Please enter a message to send: ");
		}
		if (destination == null)
		{
			while (destination == null)
			{
				try
				{
					destination = new InternetAddress(asker.askOpenEnded("Please enter a message recipient: "));
				} catch (AddressException e)
				{
					System.out.println("Sorry, you entered an invalid email address.");
				}
			}
		}
		if (subject == null)
		{
			subject = asker.askOpenEnded("Please enter a subjectJ: ");
		}
		try
		{
			MimeMessage msg = new MimeMessage(super.session);
			msg.setSubject(this.subject);
			msg.setText(this.text);
			msg.setFrom(new InternetAddress(super.username));
			msg.addRecipient(Message.RecipientType.TO, this.destination);
			Transport t = super.session.getTransport("smtps");
			t.connect(super.host, super.username, super.password);
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (MessagingException mex)
		{
			throw new MailException(mex.getMessage());
		}
		super.addMessagesToDB();
		System.out.println("Successfully sent message.");
	}

	/**
	 * Sets the text of the message.
	 * 
	 * @param text - String of what to send. Must not be null;
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	public void setRecipient(String destination)
	{
		try
		{
			this.destination = new InternetAddress(destination);
		} catch (AddressException e)
		{
			throw new MailException("The recipient " + destination
					+ " isn't a valid address.");
		}
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void clearInfo()
	{
		this.destination = null;
		this.subject = null;
		this.text = null;
	}
}
