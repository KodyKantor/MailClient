package mailClient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This is a singleton class for the mySQL database. To avoid conflict with
 * having more than one instance of the DB open at once, it is implemented as a
 * singleton class
 * 
 * @author Kody Kantor
 * 
 */

public class DatabaseAccessor
{
	private static DatabaseAccessor database = new DatabaseAccessor();
	private Connection conn;
	private Statement stmt;

	private DatabaseAccessor()
	{
		String sql = null;
		try
		{// initialize the mysql driver
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e)
		{
			System.out.println("Error connecting to database: "
					+ e.getMessage());
		}

		try
		{// connect to the MailDB database
			this.conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/MailDB", "root", "12345");
			this.stmt = conn.createStatement();
		} catch (SQLException e)
		{
			try
			{// couldn't connect to the MailDB, so let's try to create MailDB
				this.conn = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/", "root", "12345");
				sql = "CREATE DATABASE MailDB";
				this.stmt = conn.createStatement();
				this.executeUpdate(sql);
				
				sql = "USE MailDB;";
				this.executeUpdate(sql);
			} catch (SQLException sqle)
			{
				throw new RuntimeException(
						"Sorry, the database couldn't be started: "
								+ sqle.getMessage());
			}
		}
//		System.out.println("Database is ready to go!");
	}
	
	/**
	 * Implementing the executeQuery method for SQL statements.
	 * This is the place to control and debug these commands.
	 * @param sql - The sql statement that will be executed
	 * @return - A ResultSet that is returned by the SQL query
	 * @throws SQLException - The class that's using this method must handle exception
	 */
	protected ResultSet executeQuery(String sql) throws SQLException
	{		
		System.out.println("Executing Query: " + sql);
		return this.stmt.executeQuery(sql);
	}

	/**
	 * Allows classes to send SQL commands to the database, with this being the
	 * central place to control and debug the commands.
	 * 
	 * @param sql - The String command that is passed in
	 * @throws SQLException - The class that uses this method must handle exception
	 */
	protected void executeUpdate(String sql) throws SQLException
	{
		System.out.println("Executing Update: " + sql);
		this.stmt.executeUpdate(sql);
	}

	/**
	 * implementation of the singleton design
	 * @return - the only DatabaseAccessor object
	 */
	public static DatabaseAccessor getInstance()
	{
		return database;
	}
}
