package mailClient;

public class MailException extends RuntimeException
{
	private static final long serialVersionUID = 1;
	
	public MailException()
	{
		super();
	}
	public MailException(String message)
	{
		super(message);
	}
}
