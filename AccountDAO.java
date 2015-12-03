package com.example.networking;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * This class demonstrates how to connect to MySQL and run some basic commands.
 * 
 * In order to use this, you have to download the Connector/J driver and add
 * its .jar file to your build path.  You can find it here:
 * 
 * http://dev.mysql.com/downloads/connector/j/
 * 
 * You will see the following exception if it's not in your class path:
 * 
 * java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/
 * 
 * To add it to your class path:
 * 1. Right click on your project
 * 2. Go to Build Path -> Add External Archives...
 * 3. Select the file mysql-connector-java-5.1.24-bin.jar
 *    NOTE: If you have a different version of the .jar file, the name may be
 *    a little different.
 *    
 * The user name and password are both "root", which should be correct if you followed
 * the advice in the MySQL tutorial. If you want to use different credentials, you can
 * change them below. 
 * 
 * You will get the following exception if the credentials are wrong:
 * 
 * java.sql.SQLException: Access denied for user 'userName'@'localhost' (using password: YES)
 * 
 * You will instead get the following exception if MySQL isn't installed, isn't
 * running, or if your serverName or portNumber are wrong:
 * 
 * java.net.ConnectException: Connection refused
 */
public class AccountDAO {

	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String db_userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String db_password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "poker";
	
	/** The name of the table we are testing with */
	private final String tableName = "user_accounts";
	
	/** The names of the database columns */
	private final String USER = "user";
	private final String PASSWORD = "password";
	private final String BALANCE = "balance";
	
	private Connection conn;
	
	public AccountDAO()
	{
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
		}
	}
	
	/**
	 * Get a new database connection
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.db_userName);
		connectionProps.put("", this.db_password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	/**
	 * Run a SQL command which does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	public Account getUserAccount(String a_userName, String a_password) throws SQLException {
	    String command = "SELECT * FROM " + this.tableName + " " +
		        	     "WHERE user='" + a_userName + "';";
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(command); // This will throw a SQLException if it fails
	        while(rs.next())
	        {
	        	String userName = rs.getString(USER);
	        	String userPassword = rs.getString(PASSWORD);
	        	double userBalance = rs.getDouble(BALANCE);
	        	if(userPassword.equals(a_password))
	        	{
	        		return new Account(userName, userPassword, userBalance);
	        	}
		        return null;
	        }
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
		return null;
	}
	
	public boolean updateUserBalance(String name, double balance) throws SQLException {
	    Statement stmt = null;
	    String command = "UPDATE " + this.tableName + " " +
	    				 "SET balance = " + balance + " " + 
	    				 "WHERE user='" + name + "';";
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(command); // This will throw a SQLException if it fails
	        return true;
	    } finally {

	    	// This will run whether we throw an exception or not
	        if (stmt != null) { stmt.close(); }
	    }
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public Connection run() {

		// Connect to MySQL
//		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		return conn;
/*
		// Create a table
		try {
		    Account userAccount = getUserAccount(conn, "013522533", "brain");
		    System.out.println(userAccount);
		
	    } catch (SQLException e) {
			System.out.println("ERROR: Could not retrieve user");
			e.printStackTrace();
			return;
		}
		
*/
	}
	
	public Connection startTheConnection()
	{
		// Connect to MySQL
		Connection conn = null;
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	
	
	
	/**
	 * Connect to the DB and do some stuff
	 */
	public static void main(String[] args) {
		AccountDAO app = new AccountDAO();
		app.run();
	}
}
