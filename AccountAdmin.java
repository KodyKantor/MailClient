package mailClient;

import java.sql.SQLException;
import java.sql.ResultSet;

public class AccountAdmin
{
	private DatabaseAccessor dba = DatabaseAccessor.getInstance();
	
	private static String username;
	private static String password;
	private static String host;
	private static boolean loggedIn = false;
	private AskUser asker;
	
	/**
	 * This constructor is the default constructor. It assumes that
	 * the user has already entered their information for connecting to
	 * the mail service. If they haven't, it will throw a custom error.
	 * 
	 */
	public AccountAdmin()
	{
		try
		{
			ResultSet set = dba.executeQuery("SELECT username, password, host FROM CREDENTIALS");
			set.first();//move to the first row (the only row)
			username = set.getString(set.findColumn("username"));
			password = set.getString(set.findColumn("password"));
			host = set.getString(set.findColumn("host"));
		}
		catch(SQLException e)
		{
			System.out.println("Error getting credentials from DB: " + e.getMessage());
		}
	}
	
	public AccountAdmin(String name, String pass)
	{
		
	}
	
	/**
	 * This constructor takes in a username, password, and mail host address
	 * It then creates a credentials table, if one doesn't already exist, and
	 * adds these credentials to the table
	 * @param username
	 * @param password
	 * @param host
	 */
	public AccountAdmin(String name, String pass, String hostaddress)
	{
		try
		{
			dba.executeUpdate("CREATE TABLE IF NOT EXISTS CREDENTIALS "+
						"(username VARCHAR(255) NOT NULL, "+
						" password VARCHAR(255) NOT NULL, "+
						" host VARCHAR(255) NOT NULL, "
						+ "PRIMARY KEY (username))");
		} catch (SQLException e)
		{//the table has already been created
			System.out.println("Error creating table: " + e.getMessage());
		}
		
		try
		{
			dba.executeUpdate("INSERT INTO CREDENTIALS "+
							"VALUES('"+name+"', '"+pass+"', '"+hostaddress+"')");
		} catch (SQLException e)
		{
			System.out.println("Error adding credentials to DB: " + e.getMessage());
		}
		
		username = name;
		password = pass;
		host = hostaddress;
	}
	
	protected String getUsername()
	{
		return username;
	}
	protected String getPassword()
	{
		return password;
	}
	protected String getHost()
	{
		return host;
	}
}
