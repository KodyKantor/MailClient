package mailClient;

public class Driver
{
	private static AskUser asker = new AskUser();
	public static void main(String[] args)
	{
//	AcountAdmin admin = new AccountAdmin(asker.askOpenEnded("Username: "), asker.askOpenEnded("Pass: "), "imap.gmail.com);
//		AccountAdmin admin = new AccountAdmin();
//		Receive rec = new Receive();
		displayMenu();
	}
	
	public static void displayMenu()
	{
		String menu = "Welcome to the amazing JavaMail service!\n\n" +
					"What would you like to do?\n" +
					"[1] Log in\n" +
					"[2] Log out\n" +
					"[3] Check mail\n" +
					"[4] Send mail\n";
		System.out.println(menu);
	}
	
	public static void sendMessage()
	{
		Send send = new Send();
		send.setRecipient(asker.askOpenEnded("Enter mail recipient [xyz@abc.com]: "));
		send.setSubject(asker.askOpenEnded("Enter a subject: " ));
		send.setText(asker.askOpenEnded("Enter text: "));
		
		boolean sendMessage = asker.askYesNo("Send message?");
		if(sendMessage)
		{
			send.sendMessage();
		}
		else
		{
			sendMessage = asker.askYesNo("Are you sure we shouldn't send the message?");
			if(sendMessage)
			{
				send.clearInfo();
				System.out.println("Message information cleared.");
			}
		}
	}

}
