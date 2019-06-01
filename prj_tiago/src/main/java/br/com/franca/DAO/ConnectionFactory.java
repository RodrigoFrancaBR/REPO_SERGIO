package br.com.franca.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	String url ="jdbc:mysql://localhost:3306/invicto_db";
	String user="root";
	String password="root";
	

	public Connection getConection(){
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
		
	}
}
