package br.com.franca.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	protected static ComboPooledDataSource dataSource;

	//Local
	
		/*public ConnectionFactory() {
			if (dataSource == null) {
				dataSource = new ComboPooledDataSource();
				try {
					System.out.println("Tentando iniciar o dataSource:");
					dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					dataSource.setJdbcUrl(
							"jdbc:sqlserver://localhost:1433;database=INVICTO_DB;"
									+ "integratedSecurity=false;");
					dataSource.setUser("root");
					dataSource.setPassword("root");								
					dataSource.setMinPoolSize(5);
					dataSource.setMaxPoolSize(5);
					System.out.println("dataSource obtido com sucesso!");
				} catch (PropertyVetoException e) {
					System.out.println("Ocorreu um erro na construção do dataSource" + e);
					e.printStackTrace();
					throw new RuntimeException(e);
				}

			} else {
				System.out.println("Você já está usando um dataSource");
			}
	}*/
		
		// Azure
	
	public ConnectionFactory() {
		if (dataSource == null) {
			dataSource = new ComboPooledDataSource();
			try {
				System.out.println("Tentando iniciar o dataSource:");
				dataSource.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				dataSource.setJdbcUrl(
						"jdbc:sqlserver://invictovestibulares-sql-srv.database.windows.net:1433;database=INVICTO_DB;"
						+ "integratedSecurity=false;");
				dataSource.setUser("Administrador");
				dataSource.setPassword("invicto!e3x");
				dataSource.setMinPoolSize(5);
				dataSource.setMaxPoolSize(5);
				System.out.println("dataSource obtido com sucesso!");
			} catch (PropertyVetoException e) {
				System.out.println("Ocorreu um erro na construção do dataSource" + e);
				e.printStackTrace();
				throw new RuntimeException(e);
			}

		} else {
			System.out.println("Você já está usando um dataSource");
		}
	}

	public Connection getConnection() {
		try {
			System.out.println("Tentando iniciar conexao com o Banco INVICTO_DB");
			return dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no getConnection()" + e);
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static void closeAll(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {

			// se rs for diferente de null e rs não estiver fechado
			if (rs != null && !rs.isClosed()) {
				rs.close();
				System.out.println("ResultSet Fechado");
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
				System.out.println("PreparedStatement Fechado");
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
				// connection = null;
				System.out.println("Connection Fechado");
				// System.out.println("Connection Null");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no closeAll" + e);
			e.printStackTrace();

		}
	}
}
