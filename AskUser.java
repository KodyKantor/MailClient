package mailClient;

import java.io.Console;
import java.util.Scanner;

public class AskUser
{
	static Scanner scanner = new Scanner(System.in);
	
	public boolean askYesNo(String question){
		System.out.print(question + " yes/no: ");
		String reply = scanner.next();
		reply.trim();
		if(reply.equalsIgnoreCase("y") || reply.equalsIgnoreCase("yes"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String askOpenEnded(String question)
	{
		System.out.print(question);
		String reply = scanner.next();
		return reply;
	}
	
	public String askSecret(String question)
	{
		System.out.println(question);
		Console console = System.console();
		char[] pw = console.readPassword();
		return new String(pw);
	}
}
