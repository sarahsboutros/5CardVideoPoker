package com.example.networking;

import java.sql.SQLException;

public class Account {
	
	private String username;
	private String password;
	private double balance;
	
	private AccountDAO accountDAO;
	
	public Account(String u, String p, double b)
	{
		username = u;
		password = p;
		balance = b;
		accountDAO = new AccountDAO();
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public boolean updateBalance(String name, double balance) throws SQLException
	{
		if(accountDAO.updateUserBalance(name, balance))
			return true;
		
		return false;		
	}
	
	public String toString()
	{
		return "username: " + username + " | password: " + password + " | balance: " + balance;
	}
}
